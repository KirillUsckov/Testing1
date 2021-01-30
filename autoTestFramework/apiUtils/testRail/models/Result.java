package apiUtils.testRail.models;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.ITestResult;
import testrail.APIClient;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private static final String ADD_ATTACHEMENT_TEXT = "add_attachment_to_result/";
    private static ITestContext itc;
    private static APIClient client;

    public static Result create(ITestContext ctx, APIClient apiClient) {
        itc = ctx;
        client = apiClient;
        return new Result();
    }
    public void setResult(ITestResult result, String comment) {
        try {
            Map data = new HashMap();
            switch (result.getStatus()){
                case ITestResult.SKIP:
                    data.put("status_id", 2);
                    break;
                case ITestResult.SUCCESS:
                    data.put("status_id", 1);
                    break;
                case ITestResult.FAILURE:
                    data.put("status_id", 5);
                    break;
                default:
                    data.put("status_id", 3);
                    break;
            }
            data.put("comment", comment);

            Long caseId = (Long) itc.getAttribute("caseId");
            Long runId = (Long) itc.getAttribute("runId");
            JSONObject res = (JSONObject)  client.sendPost("add_result_for_case/" + runId + "/" + caseId, data);
            Long result_id = (Long) res.get("id");
            itc.setAttribute("resultId", result_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addScreenshot(String path) {
        try {
            String uri = ADD_ATTACHEMENT_TEXT + itc.getAttribute("resultId");
            client.sendPost(uri ,path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
