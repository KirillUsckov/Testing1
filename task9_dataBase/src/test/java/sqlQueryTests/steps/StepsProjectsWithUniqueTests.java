package sqlQueryTests.steps;

import logger.Logger;
import sqlQueryTests.enums.Columns;

import java.sql.ResultSet;
import java.util.ArrayList;

public class StepsProjectsWithUniqueTests {
    private static Logger logger = new Logger(StepsProjectsWithUniqueTests.class);

    private static ArrayList<String> projectNames = new ArrayList<>();
    private static ArrayList<String> testAmountArray = new ArrayList<>();

    public static ArrayList<String> getResult(ResultSet resultSet) {
        try {
            reCreateProjectNamesAndTestsCountArrays();
            while (resultSet.next()) {
                String projectName = resultSet.getString(
                                                Columns.PROJECT_NAME.name().toLowerCase());
                int testAmount = resultSet.getInt(
                                                Columns.UNIQUE_TESTS.name().toLowerCase());
                projectNames.add(projectName);
                testAmountArray.add(String.valueOf(testAmount));
            }
            projectNames = CommonSteps.getCorrectNames(projectNames);
            return getProjectsNamesAndTestAmountString();
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    private static ArrayList<String> getProjectsNamesAndTestAmountString() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < projectNames.size(); i++) {
            result.add(projectNames.get(i) + " | " + testAmountArray.get(i));
        }
        return result;
    }

    public static void reCreateProjectNamesAndTestsCountArrays() {
        projectNames = CommonSteps.reCreateProjectNamesArrays(projectNames);
        testAmountArray.removeAll(testAmountArray);
        testAmountArray.add("Test amount");
    }

}
