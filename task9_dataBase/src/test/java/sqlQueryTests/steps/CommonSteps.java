package sqlQueryTests.steps;

import logger.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CommonSteps {
    private static Logger logger = new Logger(CommonSteps.class);

    public static ResultSet executeQuery(String sql, Statement statement) {
        try {
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

    public static ArrayList<String> getCorrectNames(ArrayList<String> elementNames) {
        int maxLength = 0;
        ArrayList<String> result = new ArrayList<>();
        for (String el: elementNames) {
            if (el.length() > maxLength) {
                maxLength = el.length();
            }
        }

        for (int i = 0; i < elementNames.size(); i++) {
            String newName = elementNames.get(i);
            while (newName.length() < maxLength) {
                newName += " ";
            }
            result.add(newName);

        }
        return result;
    }

    public static ArrayList<String> reCreateProjectNamesArrays(ArrayList<String> projectsNamesArray) {
        projectsNamesArray.removeAll(projectsNamesArray);
        projectsNamesArray.add("Project name");
        return projectsNamesArray;
    }

    public static ArrayList<String> reCreateTestNamesArrays(ArrayList<String> testsNamesArray) {
        testsNamesArray.removeAll(testsNamesArray);
        testsNamesArray.add("Test name");
        return testsNamesArray;
    }

    public static void writeResult(ArrayList<String> result) {
        for (String el: result) {
            logger.queryResult(el);
        }
    }


}
