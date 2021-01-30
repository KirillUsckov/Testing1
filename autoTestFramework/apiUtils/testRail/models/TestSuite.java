package apiUtils.testRail.models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.ITestContext;
import testrail.APIClient;

import java.util.HashMap;
import java.util.Map;

public class TestSuite {
    private static final String GET_SUITES = "get_suites/";
    private static final String ADD_SUITE = "add_suite/";
    private static final String UPDATE_SUITE = "update_suite/";
    private static APIClient client;
    private static String projectId;
    private static String suiteName;
    private static ITestContext ctx;
    private Long suiteId;

    public static TestSuite create(ITestContext itc, String prjctId, APIClient apiClient, String name) {
        client = apiClient;
        projectId = prjctId;
        suiteName = name;
        ctx = itc;
        return new TestSuite();
    }

    public String getAll() {
        try {
            JSONArray response = (JSONArray) client.sendGet(GET_SUITES + projectId);
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String add(String description) {
        Map data = new HashMap();
        data.put("name", suiteName);
        data.put("description", description);
        return sendAddQuery(data);
    }

    public String add() {
        Map data = new HashMap();
        data.put("name", suiteName);
        return sendAddQuery(data);
    }

    private String sendAddQuery(Map data) {
        try {
            String uri = ADD_SUITE + projectId;
            JSONObject response = (JSONObject) client.sendPost(uri, data);
            Long suite_id = (Long) response.get("id");
            suiteId = suite_id;
            ctx.setAttribute("suiteId", suiteId);
            return response.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String update() {
        try {
            String uri = UPDATE_SUITE + suiteId.toString();
            JSONObject response = (JSONObject) client.sendPost(uri, suiteName);
            return response.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String run(String runName) {
        return Run.start(runName, suiteId, projectId, client, ctx);
    }

    public void addSection(String name) {
        Section.add(name, projectId, suiteId, client, ctx);
    }

}
