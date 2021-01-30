package apiUtils.testRail.models;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import testrail.APIClient;

import java.util.HashMap;
import java.util.Map;

public class Run {

    public static String start(String runName, Long suiteId, String projectId, APIClient client, ITestContext itc) {
        try {
            Map data = new HashMap();
            data.put("suite_id", suiteId);
            data.put("include_all", true);
            data.put("name", runName);
            String uri = "add_run/" + projectId;
            JSONObject c = (JSONObject) client.sendPost(uri, data);
            Long run_id = (Long) c.get("id");
            itc.setAttribute("runId", run_id);
            return c.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
