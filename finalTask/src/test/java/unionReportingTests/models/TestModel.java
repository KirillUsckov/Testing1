package unionReportingTests.models;

import unionReportingTests.enums.AttachType;
import unionReportingTests.sysUtils.SystemUtil;

import java.io.File;
import java.sql.Timestamp;

public class TestModel {
    private String methodName, projectId, projectName;
    private String testStatusId, log;
    private String startTime, endTime;
    private String environment, testName = "name";
    private int isException;
    private int testId;
    private File screenshot;
    private AttachType attachType;

    public void setMethodName(String name) {
        methodName = name;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setTestStatusId(String statusId) {
        testStatusId = statusId;
    }

    public void setIsException(boolean isException) {
        this.isException = isException ? 1 : 0;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public void setTestName(String name) {
        testName = name;
    }

    public void setStartTime(Timestamp ts) {
        startTime = String.valueOf(ts);
        startTime = startTime.substring(0, startTime.indexOf("."));
    }

    public void setEndTime(Timestamp ts) {
        endTime = String.valueOf(ts);
        endTime = endTime.substring(0, endTime.indexOf("."));
    }

    public void setScreenshot(String screenshotPath) {
        screenshot = new File(screenshotPath);
        attachType = AttachType.IMG;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public void setProjectName(String prjctName) {
        projectName = prjctName;
    }

    public void setEnvironment() {
        environment = SystemUtil.getPCName();
    }

    public String getMethodName() {
        return methodName;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getTestName() {
        return testName;
    }

    public String getStatusId() {
        return testStatusId;
    }

    public String getTextStatus() {
        return testStatusId == "0" ? "Failed" :
                    testStatusId == "1" ? "Passed" :
                        testStatusId == "2" ? "Skipped" : "Unfinished";
    }

    public String getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public int getIsException() {
        return isException;
    }

    public String getLog() {
        return log;
    }

    public int getTestId() {
        return testId;
    }

    public File getScreenshot() {
        return screenshot;
    }

    public int getScreenLength() {
        return (int) screenshot.length();
    }

    public String getAttachType() {
        return attachType.get();
    }

    public String getEnvironment() {
        return environment;
    }

}
