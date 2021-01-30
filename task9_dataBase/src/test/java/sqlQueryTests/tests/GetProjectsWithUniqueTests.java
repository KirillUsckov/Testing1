package sqlQueryTests.tests;

import baseTest.BaseTest;
import fileUtils.File;
import logger.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sqlQueryTests.steps.CommonSteps;
import sqlQueryTests.steps.StepsProjectsWithUniqueTests;

import java.sql.ResultSet;
import java.util.ArrayList;

public class GetProjectsWithUniqueTests extends BaseTest {
    private Logger logger;

    @BeforeTest
    public void setStatement() {
        dbConnector.setStatement();
        logger = new Logger(GetProjectsWithUniqueTests.class);
    }

    @Test
    public void allProjectsWithUniqueTestsAmount() {
        logger.testCase("allProjectsWithUniqueTestsAmount");
        File file =  new File("src/test/resources/GetProjectsWithUniqueTestsCountQuery.txt");
        String sql = file.read();

        logger.info("Result of getAllProjectsWithUniqueTestsAmount query:");
        ResultSet resultSet = CommonSteps.executeQuery(sql, dbConnector.getStatement());
        ArrayList<String> result = StepsProjectsWithUniqueTests.getResult(resultSet);
        CommonSteps.writeResult(result);
    }

}
