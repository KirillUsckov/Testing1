package sqlQueryTests.tests;

import baseTest.BaseTest;
import fileUtils.File;
import logger.Logger;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sqlQueryTests.steps.CommonSteps;
import sqlQueryTests.steps.StepsMinTimeOfEveryTest;

import java.sql.ResultSet;
import java.util.ArrayList;

public class GetMinTimeOfEveryTest extends BaseTest {
    private Logger logger;

    @BeforeTest
    public void statementInit() {
        logger = new Logger(GetMinTimeOfEveryTest.class);
        dbConnector.setStatement();
    }

    @Test
    public void minTimeOfEveryTest() {
        logger.testCase("minTimeOfEveryTest");
        File file = new File("src/test/resources/GetMinTimeOfEveryTestQuery.txt");
        String sql = file.read();

        logger.info("Result of getMinTimeOfEveryTest query:");
        ResultSet resultSet = CommonSteps.executeQuery(sql, dbConnector.getStatement());
        ArrayList<String> result = StepsMinTimeOfEveryTest.getResult(resultSet);
        CommonSteps.writeResult(result);
    }
}
