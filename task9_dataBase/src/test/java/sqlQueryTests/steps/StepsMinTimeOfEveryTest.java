package sqlQueryTests.steps;

import logger.Logger;
import sqlQueryTests.enums.Columns;

import java.sql.ResultSet;
import java.util.ArrayList;

public class StepsMinTimeOfEveryTest {
    private static Logger logger = new Logger(StepsMinTimeOfEveryTest.class);
    private static ArrayList<String> projectNames = new ArrayList<>();
    private static ArrayList<String> testNames = new ArrayList<>();

    private static ArrayList<String> getProjectsAndTestsNamesAndMinTime(ArrayList<String> minTime) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < projectNames.size(); i++) {
            result.add(projectNames.get(i) + " | " + testNames.get(i) + " | " + minTime.get(i));
        }
        return result;
    }

    public static ArrayList<String> getResult(ResultSet resultSet) {
        try {
            reCreateProjectAndTestNamesArrays();
            ArrayList<String> minTimeArray = new ArrayList<>();
            minTimeArray.add("Min time");
            while (resultSet.next()) {
                projectNames.add(resultSet.getString(
                                            Columns.PROJECT_NAME.name().toLowerCase()));
                testNames.add(resultSet.getString(
                                            Columns.TEST_NAME.name().toLowerCase()));
                minTimeArray.add( resultSet.getString(
                                            Columns.MIN_TIME.name().toLowerCase()));
            }
            projectNames = CommonSteps.getCorrectNames(projectNames);
            testNames = CommonSteps.getCorrectNames(testNames);
            return getProjectsAndTestsNamesAndMinTime(minTimeArray);
        }catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    private static void reCreateProjectAndTestNamesArrays() {
        projectNames = CommonSteps.reCreateProjectNamesArrays(projectNames);
        testNames = CommonSteps.reCreateTestNamesArrays(testNames);
    }
}
