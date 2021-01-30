package sqlQueryTests.tests;

import baseTest.BaseTest;
import fileUtils.File;
import logger.Logger;
import org.testng.annotations.BeforeSuite;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sqlQueryTests.steps.CommonSteps;
import sqlQueryTests.steps.StepsChromeFirefoxTests;

import java.sql.ResultSet;

public class GetChromeFirefoxTests extends BaseTest {
    private Logger logger;

    @DataProvider (name = "sqlTestCases")
    public Object[][] setBrowsers() {
        return new Object[][] {{"firefox", "chrome"}};
    }

    @BeforeTest
    public void statementInit() {
        logger = new Logger(GetChromeFirefoxTests.class);
        dbConnector.setStatement();
    }

    @Test (dataProvider = "sqlTestCases")
    public void chromeAndFirefoxTestsAmount(String fBrowser, String sBrowser) {
        logger.testCase("chromeAndFirefoxTestsAmount");
        File sqlFile = new File("src/test/resources/GetChromeAndFirefoxTestsQuery.txt");
        String sql = sqlFile.read();
        sql = sql.replace("fBrowser", fBrowser);
        sql = sql.replace("sBrowser", sBrowser);

        logger.info("Result of getAllProjectsWithDateAfterSpecific query:");
        ResultSet resultSet = CommonSteps.executeQuery(sql, dbConnector.getStatement());

        logger.queryResult("Test amount");
        StepsChromeFirefoxTests.writeIntResult(resultSet);
    }

}
