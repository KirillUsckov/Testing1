package apiUtils.testRail;

import apiUtils.testRail.models.Result;
import apiUtils.testRail.models.TestSuite;
import io.restassured.response.Response;
import org.apache.commons.codec.language.bm.Lang;
import org.testng.ITestContext;
import org.testng.ITestResult;
import testrail.APIClient;
import org.json.simple.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class TestRail {
    private ITestContext itc;
    private APIClient client;
    private Result testResult;

    private TestRail(String server, String userName, String password, ITestContext itc) {
        client = new APIClient(server);
        client.setUser(userName);
        client.setPassword(password);
        this.itc = itc;
    }

    public static TestRail create(String url, String userName, String password, ITestContext itc) {
        return new TestRail(url, userName, password, itc);
    }

    public APIClient getClient() {
        return client;
    }

    public String post(String uri, int statusId, String comment) {
        return sendPost(uri, statusId, comment);
    }

    public String post(String uri, int statusId) {
        return sendPost(uri, statusId, null);
    }

    private String sendPost(String uri, int statusId, String comment) {
        try {
            Map data = new HashMap();
            data.put("status_id", new Integer(statusId));
            if (comment != null) {
                data.put("comment", comment);
            }
            JSONObject r = (JSONObject) client.sendPost(uri, data);
            return r.toJSONString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    public TestSuite addSuite(String projectId, String name) {
        try {
            TestSuite testSuite = TestSuite.create(itc, projectId, client, name);
            return testSuite;
        } catch (Exception e) {
            return null;
        }
    }


    public void setResult(ITestResult result, String comment) {
        testResult = Result.create(itc, client);
        testResult.setResult(result, comment);
    }

    public void addRunScreenshot(String path) {
        testResult.addScreenshot(path);
    }

}
