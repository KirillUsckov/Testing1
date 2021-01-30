package unionReportingTests.urApiUtils;

import apiUtils.APIUtils;
import io.restassured.response.Response;
import logger.Logger;
import unionReportingTests.enums.Query;

public class URApiUtil {
    private static final Logger LOG = new Logger(URApiUtil.class);
    private String server, username, password;

    public URApiUtil(String url, String username, String password) {
        LOG.info("Initialization of URApiUtil class");
        server = url;
        this.username = username;
        this.password = password;
    }

    public String getToken(String variant) {
        LOG.info("Set request url");
        String url = server + Query.GET_TOKEN.getQuery();
        LOG.info("Send request to get token");
        Response response = APIUtils.postWithParamAndAuth(url, username, password, "variant", variant);

        LOG.info("Return token");
        return response.getBody().asString();
    }

    public String getTestList(String projectId) {
        LOG.info("Set request url");
        String url = server + Query.GET_TESTS_LIST_IN_JSON.getQuery();
        LOG.info("Send request to get test list");

        Response response = APIUtils.postWithParamAndAuth(url, username, password,
                                                            "projectId", projectId);

        LOG.info("Return test list");
        return response.getBody().asString();
    }
}
