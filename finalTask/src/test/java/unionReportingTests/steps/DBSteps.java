package unionReportingTests.steps;

import apiUtils.APIUtils;
import dbUtils.DBConnector;
import logger.Logger;
import unionReportingTests.enums.AttachType;
import unionReportingTests.models.TestModel;
import unionReportingTests.sysUtils.SystemUtil;

import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;

public class DBSteps {
    private static final Logger LOG = new Logger(DBSteps.class);
    private static final String ADD_SESSION = "src/test/resources/sqlQuery/AddSession.sql";
    private static final String GET_LAST_SESSION_ID = "src/test/resources/sqlQuery/GetLastSessionId.sql";
    private static final String ADD_TEST = "src/test/resources/sqlQuery/AddTest.sql";
    private static final String GET_TEST_ID = "src/test/resources/sqlQuery/GetTestIdQuery.sql";
    private static final String ADD_LOG = "src/test/resources/sqlQuery/AddLogForTest.sql";
    private static final String ADD_ATTACHMENT = "src/test/resources/sqlQuery/AddAttachment.sql";
    private static final String GET_TEST_ATTACHMENT = "src/test/resources/sqlQuery/GetTestAttachment.sql";

    private DBSteps() {}

    public static void addTestWithAttach(DBConnector dbConnector, TestModel test) {
        LOG.info("SET start and end time");
        dbConnector.setAutoCommit(true);

        LOG.info("ADD session");
        addSession(dbConnector, String.valueOf(System.currentTimeMillis()), test.getStartTime());

        dbConnector.executeQueryFile(GET_LAST_SESSION_ID);
        //Получаем максимальный status_id, он же - id последней созданной сессии
        String sessionId = getSessionId(dbConnector);

        addTest(dbConnector, test, sessionId);

        LOG.info("GET test id");
        int testId = getTestId(dbConnector, test.getMethodName(), sessionId);
        test.setTestId(testId);

        LOG.info("ADD log");
        addLog(dbConnector, test);

        LOG.info("ADD attachment");
        addScreenshot(dbConnector, test);

    }

    private static void addSession(DBConnector dbConnector, String sessionKey, String createdTime) {
        String query = dbConnector.getQuery(ADD_SESSION);
        query = query.replace("sesKey", sessionKey);
        query = query.replace("createdTime", createdTime);

        dbConnector.executeUpdate(query);
    }

    private static void addTest(DBConnector dbConnector, String methName, String prjtId, String sessionId,
                                                            String statusId, String startTime, String endTime) {
        String query = dbConnector.getQuery(ADD_TEST);
        String hostname = SystemUtil.getPCName();

        query = query.replace("host", hostname);
        query = query.replace("methName", methName);
        query = query.replace("prId", prjtId);
        query = query.replace("sesId", sessionId);
        query = query.replace("statId", statusId);
        query = query.replace("stTime", startTime);
        query = query.replace("endTime", endTime);

        dbConnector.executeUpdate(query);
    }

    private static void addTest(DBConnector dbConnector, TestModel test, String sessionId) {
        String query = dbConnector.getQuery(ADD_TEST);

        query = query.replace("host", test.getEnvironment());
        query = query.replace("methName", test.getMethodName());
        query = query.replace("prId", test.getProjectId());
        query = query.replace("sesId", sessionId);
        query = query.replace("statId", test.getStatusId());
        query = query.replace("stTime", test.getStartTime());
        query = query.replace("endTime", test.getEndTime());

        dbConnector.executeUpdate(query);
    }

    private static String getSessionId(DBConnector dbConnector) {
        String query = dbConnector.getQuery(GET_LAST_SESSION_ID);
        ResultSet resultSet = dbConnector.executeQuery(query);

        return APIUtils.getResult(resultSet, "max_session_id").get(0);
    }

    private static int getTestId(DBConnector dbConnector, String methName, String sessionId) {
        String query = dbConnector.getQuery(GET_TEST_ID);
        query = query.replace("methName", methName);
        query = query.replace("sessionId", sessionId);

        ResultSet resultSet = dbConnector.executeQuery(query);

        int testId = Integer.parseInt(APIUtils.getResult(resultSet, "testId").get(0));

        return testId;
    }

    private static void addLog(DBConnector dbConnector, TestModel test) {
        String query = dbConnector.getQuery(ADD_LOG);

        dbConnector.prepareStatement(query);
        dbConnector.setString(1, test.getLog());
        dbConnector.setInt(2, test.getIsException());
        dbConnector.setInt(3, test.getTestId());
        dbConnector.execute();

        dbConnector.closePrepStatement();
    }

    private static void addScreenshot(DBConnector dbConnector, TestModel test) {
        String query = dbConnector.getQuery(ADD_ATTACHMENT);
        dbConnector.setAutoCommit(false);
        try {
            FileInputStream fis = new FileInputStream(test.getScreenshot());
            dbConnector.prepareStatement(query);

            dbConnector.setBinaryStream(1, fis, test.getScreenLength());

            dbConnector.setString(2, test.getAttachType());
            dbConnector.setInt(3, test.getTestId());

            dbConnector.executeUpdate();
            dbConnector.commit();
            fis.close();
            dbConnector.close();
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    public static int getScreenshot(DBConnector dbConnector, TestModel test) {
        String query = dbConnector.getQuery(GET_TEST_ATTACHMENT);
        query = query.replace("testId", String.valueOf(test.getTestId()));
        System.out.println(query);
        ResultSet resultSet = dbConnector.executeQuery(query);
        return Integer.parseInt(APIUtils.getResult(resultSet, "content").get(0));
    }

}
