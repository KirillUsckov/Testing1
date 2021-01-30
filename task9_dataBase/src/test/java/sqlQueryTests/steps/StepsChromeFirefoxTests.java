package sqlQueryTests.steps;

import logger.Logger;
import sqlQueryTests.enums.Columns;


import java.sql.ResultSet;

public class StepsChromeFirefoxTests {
    private static Logger logger = new Logger(StepsChromeFirefoxTests.class);

    public static void writeIntResult(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                int testsCount = resultSet.getInt(Columns.COUNT.name().toLowerCase());
                logger.queryResult(testsCount);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

}
