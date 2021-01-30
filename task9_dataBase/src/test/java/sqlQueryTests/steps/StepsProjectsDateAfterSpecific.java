package sqlQueryTests.steps;

import logger.Logger;
import sqlQueryTests.enums.Columns;

import java.sql.ResultSet;
import java.util.ArrayList;

public class StepsProjectsDateAfterSpecific {
    private static Logger logger = new Logger(StepsProjectsDateAfterSpecific.class);
    private static ArrayList<String> testDates = new ArrayList<>();
    private static ArrayList<String> projectNames = new ArrayList<>();
    private static ArrayList<String> testNames = new ArrayList<>();

    public static ArrayList<String> getResult(ResultSet resultSet) {
        try {
            reCreateProjectAndTestNamesArrays();
            testDates.add("Date");
            while (resultSet.next()) {
                projectNames.add(resultSet.getString(
                                            Columns.PROJECT_NAME.name().toLowerCase()));
                testNames.add(resultSet.getString(
                                            Columns.TEST_NAME.name().toLowerCase()));
                testDates.add(resultSet.getString(
                                            Columns.TEST_START_DATE.name().toLowerCase()));
            }
            projectNames = CommonSteps.getCorrectNames(projectNames);
            testNames = CommonSteps.getCorrectNames(testNames);
            return getProjectsAndTestsNamesAndDate(projectNames, testNames, testDates);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    private static ArrayList<String> getProjectsAndTestsNamesAndDate(ArrayList<String> projectNames,
                                                                     ArrayList<String> testsName,
                                                                     ArrayList<String> date) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < projectNames.size(); i++) {
            result.add(projectNames.get(i) + " | " + testsName.get(i) + " | " + date.get(i));
        }
        return result;
    }

    public static void reCreateProjectAndTestNamesArrays() {
        projectNames = CommonSteps.reCreateProjectNamesArrays(projectNames);
        testNames = CommonSteps.reCreateTestNamesArrays(testNames);
    }


}
