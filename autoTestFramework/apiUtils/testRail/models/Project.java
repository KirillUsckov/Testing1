package apiUtils.testRail.models;

import org.json.simple.JSONArray;
import testrail.APIClient;

public class Project {

    public String getAllProjects(APIClient client) {
        try {
            JSONArray response = (JSONArray) client.sendGet("get_projects");
            return response.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
