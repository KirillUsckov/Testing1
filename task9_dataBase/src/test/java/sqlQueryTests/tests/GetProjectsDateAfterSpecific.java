package sqlQueryTests.tests;

import baseTest.BaseTest;
import fileUtils.File;
import logger.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sqlQueryTests.steps.CommonSteps;
import sqlQueryTests.steps.StepsProjectsDateAfterSpecific;

import java.sql.ResultSet;
import java.util.ArrayList;

public class GetProjectsDateAfterSpecific extends BaseTest {
    private Logger logger;

    @BeforeTest
    public void setStatement() {
        logger = new Logger(GetProjectsDateAfterSpecific.class);
        dbConnector.setStatement();
    }

    @Test(parameters = "minimumDate")
    public void allProjectsWithDateAfterSpecific(String date) {
        logger.testCase("allProjectsWithDateAfterSpecific");
        File file = new File("src/test/resources/GetProjectsWithDateAfterSpecificQuery.txt");
        String sql = file.read();
        if (sql.contains("testData")) {
            sql = sql.replace("testData", date);
        }

        logger.info("Result of getAllProjectsWithDateAfterSpecific query:");
        ResultSet resultSet = CommonSteps.executeQuery(sql, dbConnector.getStatement());
        ArrayList<String> result = StepsProjectsDateAfterSpecific.getResult(resultSet);
        CommonSteps.writeResult(result);
    }
}
