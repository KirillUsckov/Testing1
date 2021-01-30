package baseTest;

import apiUtils.testRail.TestRail;
import apiUtils.testRail.models.TestCase;
import apiUtils.testRail.models.TestSuite;
import browsersUtils.WebDriverInitialization;
import constants.CommonConstants;
import dbUtils.DBConnector;
import dbUtils.DBData;
import enums.AuthData;
import logger.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import screenshot.Screenshot;
import stringsUtils.XMLStringsReader;

public class BaseTest {
    private static final Logger LOG = new Logger(BaseTest.class);
    protected XMLStringsReader strReader;
    protected String siteUrl;
    private String suiteName;
    private String projectId;
    protected DBConnector dbCon;
    protected String screenshotPath;
    protected TestRail testRail;
    protected TestSuite testSuite;
    protected TestCase testCase;
    protected String server;
    protected String username, password;

    @BeforeSuite
    public void openBrowser() {
        WebDriverInitialization.setBrowser();
    }

    @BeforeSuite
    public void setUp() {
        strReader = new XMLStringsReader(CommonConstants.SETTINGS_PATH);
        siteUrl = strReader.getCustomElement(CommonConstants.SITE_URL_TEXT);
        server = strReader.getCustomElement(CommonConstants.API_SERVER);


        strReader = new XMLStringsReader(CommonConstants.TESTRAIL_SUITE_DATA_PATH);
        screenshotPath = strReader.getCustomElement(CommonConstants.SCREENSHOT_TEXT);
        suiteName = strReader.getCustomElement(CommonConstants.SUITE_NAME_TEXT);
        projectId = strReader.getCustomElement(CommonConstants.PROJECT_ID_TEXT);
    }


    @BeforeSuite
    public void setUpTestRail(ITestContext itc) {
        strReader = new XMLStringsReader(CommonConstants.TR_SETTINGS);
        String testRailUri = strReader.getCustomElement(CommonConstants.TESTRAIL_URI_TEXT);
        testRail = TestRail.create(testRailUri,
                System.getProperty(AuthData.TRUSERNAME.get()),
                System.getProperty(AuthData.TRPASSWORD.get()), itc);
        testSuite = testRail.addSuite(projectId, suiteName);
        addTestSuite();
        testSuite.run(strReader.getCustomElement(CommonConstants.RUN_NAME_TEXT));
        testSuite.addSection(strReader.getCustomElement(CommonConstants.SECTION_NAME_TEXT));

        dataBaseInitialization();
    }

    public void dataBaseInitialization() {
        username = System.getProperty(AuthData.DBUSERNAME.get());
        password = System.getProperty(AuthData.DBPASSWORD.get());

        siteUrl = String.format(siteUrl, username, password);
        DBData data = DBData.setDataToConnect(username, password);
        dbCon = data.getConnector();
        dbCon.connect();
    }

    private void addTestSuite() {
        String description = strReader.getCustomElement(CommonConstants.SUITE_DESCRIPTION_TEXT);
        if (description.length() != 0) {
            testSuite.add(strReader.getCustomElement(CommonConstants.SUITE_DESCRIPTION_TEXT));
        } else {
            testSuite.add();
        }
    }

    @BeforeMethod
    public void setCaseId(ITestContext itc) {
        testCase = TestCase.create(testRail.getClient(), itc);
    }

    @AfterMethod
    public void afterTest(ITestResult result, ITestContext ctx) {
        testRail.setResult(result, strReader.getCustomElement(CommonConstants.RESULT_COMMENT_TEXT));
        testRail.addRunScreenshot(screenshotPath);
        Screenshot.delete(screenshotPath);
    }

    @AfterTest
    public void closeConnection() {
        try {
            dbCon.close();
        } catch (Exception e) {
            LOG.error("Something went wrong with DBConnector: " + e.getMessage());
        }
    }
    @AfterSuite
    public void finish() {
        WebDriverInitialization.quiteBrowser();
    }
}
