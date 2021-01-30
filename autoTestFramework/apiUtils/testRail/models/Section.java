package apiUtils.testRail.models;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import testrail.APIClient;

import java.util.HashMap;
import java.util.Map;

public class Section {

    public static void add(String name, String projectId, Long suiteId, APIClient client, ITestContext ctx) {
        try {
            Map data = new HashMap();
            data.put("name", name);
            data.put("suite_id", suiteId);
            String uri = "add_section/" + projectId;
            JSONObject c = (JSONObject) client.sendPost(uri, data);
            Long section_id = (Long) c.get("id");
            ctx.setAttribute("sectionId", section_id);
            System.out.println(c.toJSONString());
        } catch (Exception e) {
            System.out.println("Section: " + e.getMessage());
        }
    }
}
