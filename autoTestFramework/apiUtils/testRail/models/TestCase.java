package apiUtils.testRail.models;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import testrail.APIClient;

import java.util.HashMap;
import java.util.Map;

public class TestCase {
    private static APIClient client;
    private static ITestContext ctx;
    private Long caseId;

    public TestCase() {

    }

    public static TestCase create(APIClient apiClient, ITestContext itc) {
        ctx = itc;
        client = apiClient;
        return new TestCase();
    }

    public void add(String title, String description) {
        Map data = new HashMap();
        data.put("title", title);
        data.put("description", description);
        data.put("suite_id", ctx.getAttribute("suiteId"));
        addCase(data);
    }

    public void add(String title){
        Map data = new HashMap();
        data.put("title", title);
        data.put("suite_id", ctx.getAttribute("suiteId"));
        addCase(data);
    }

    private void addCase(Map data) {
        try {
            JSONObject response = (JSONObject) client.sendPost("add_case/" + ctx.getAttribute("sectionId"), data);
            Long case_id = (Long) response.get("id");
            ctx.setAttribute("caseId", case_id);
            caseId = case_id;
        } catch (Exception e) {
        }
    }

}
