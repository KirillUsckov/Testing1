package unionReportingTests;

import baseTest.BaseTest;
import logger.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import screenshot.Screenshot;
import unionReportingTests.models.TestModel;
import unionReportingTests.pageObjects.MainPage;
import unionReportingTests.pageObjects.MainPageIFrame;
import unionReportingTests.pageObjects.SpecificProjectPage;
import unionReportingTests.steps.CookieSteps;
import unionReportingTests.steps.DBSteps;
import unionReportingTests.steps.UISteps;
import unionReportingTests.urApiUtils.URApiUtil;

import java.sql.Timestamp;

public class UnionReporting extends BaseTest {

    private static final Logger LOG = new Logger(UnionReporting.class);
    private MainPage mainPage;
    private SpecificProjectPage prjctPage;
    private MainPageIFrame mainPageIFrame;
    private URApiUtil api;
    private TestModel test;

    @DataProvider (name = "SomeTests")
    public Object[][] setProject() {
        return new Object[][] {{"Nexage", "test_project", "1", "1", false}};
    }

    @BeforeTest
    public void initURApiUtil() {
        api = new URApiUtil(server, username, password);
        mainPage = new MainPage(siteUrl);
        mainPageIFrame = new MainPageIFrame();
        dbCon.setStatement();
        prjctPage = new SpecificProjectPage();
    }

    @BeforeMethod
    public void createTest() {
        test = new TestModel();
        test.setStartTime(new Timestamp(System.currentTimeMillis()));
        test.setScreenshot(screenshotPath);
    }

    @BeforeMethod
    public void setTestCase() {
        LOG.testCase();
    }

    @Test (dataProvider = "SomeTests")
    public void checkActionsWithTestProjects(String prjctName, String newPrgctName, String varNumber,
                                                                    String statusId, boolean isExcep) {
        test.setTestStatusId(statusId);
        test.setIsException(isExcep);
        test.setEnvironment();
        String methName = new Object(){}.getClass().getEnclosingMethod().getName();
        test.setMethodName(methName);
        testCase.add(methName, "Tests for union reporting");

        LOG.step("GET token");

        String token = api.getToken(varNumber);
        Assert.assertNotNull(token, "Access token isn't got");

        LOG.step("ADD cookie with token");
        mainPage.openPage();
        Assert.assertTrue(mainPage.getUrl().contains("projects"), "Projects page isn't open");

        CookieSteps.addCookieToken(mainPage, token);

        Assert.assertTrue(mainPage.isPageContainsCorrectVar(varNumber), "Page doesn't have correct variant number");

        LOG.step("GET test list");
        String prjctId = UISteps.getProjectId(mainPage, prjctName);
        mainPage.clickOnProject(prjctName);
        String testsResponse = api.getTestList(prjctId);

        Assert.assertTrue(prjctPage.isDatesSorted(), "Tests aren't sorted in descending order of date");
        Assert.assertTrue(prjctPage.isTestCorrect(testsResponse), "Tests don't equal tests received on request");

        LOG.step("ADD new project");
        prjctPage.goToHome();
        UISteps.addNewProject(mainPage, mainPageIFrame, newPrgctName);
        mainPage.reloadPage();
        test.setProjectName(newPrgctName);
        prjctId = UISteps.getProjectId(mainPage, newPrgctName);
        test.setProjectId(prjctId);
        Assert.assertFalse(mainPageIFrame.isFrameClosed("modal-open"), "IFame wasn't closed");

        LOG.step("ADD test");
        String log = "Some log data. Some log data. Some log data. Some log data. Some log data. Some log data.";
        test.setLog(log);
        test.setEndTime(new Timestamp(System.currentTimeMillis()));

        mainPage.clickOnProject(newPrgctName);

        Screenshot.create(screenshotPath);
        DBSteps.addTestWithAttach(dbCon, test);
        Assert.assertTrue(prjctPage.isFirstTestDisplay(), "Test doesn't display");
        prjctPage.clickOnFirstTest();
        Assert.assertTrue(UISteps.isStartTimeCorrect(test), "Start time doesn't match with expected time");
        Assert.assertTrue(UISteps.isEndTimeCorrect(test), "End time doesn't match with expected time");
        Assert.assertTrue(UISteps.isEnvironmentCorrect(test), "Actual environment doesn't match with " +
                                                                                        "expected environment");
        Assert.assertTrue(UISteps.isLogCorrect(test), "Actual log doesn't match with expected log");
        Assert.assertTrue(UISteps.isStatusCorrect(test), "Actual status doesn't match with expected status");
        Assert.assertTrue(UISteps.isTestNameCorrect(test), "Actual status doesn't match with expected status");
        Assert.assertTrue(UISteps.isImgCorrect(test), "Actual img doesn't match with expected img");

    }

}