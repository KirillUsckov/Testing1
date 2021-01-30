package apiUtils;

import enums.APIResponses;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import logger.Logger;
import org.json.JSONObject;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class APIUtils {
    private static final Logger LOG = new Logger(APIUtils.class);
    
    
    public static Response get(String url) {
        Response getResponse = given()
                .when()
                .get(url)
                .then()
                .extract()
                .response();
        return getResponse;
    }

    public static Response getWithCodeAssert(String url, int code) {
        Response getResponse = given()
                .when()
                .get(url)
                .then()
                .assertThat()
                .statusCode(code)
                .extract()
                .response();
        return getResponse;
    }
    
    //TODO Доработать логику
    public static Response query(APIResponses apiResponse, String jsonBodyText, String url) {
        JSONObject object = new JSONObject(jsonBodyText);
        /*switch (apiResponse) {
            case POST:
                post(object, url);
                break;
            case PUT:
                break;
            case PATCH:
                break;
        }*/
        return post(object, url);
    }
    
    private static Response post(JSONObject object, String url) {
        Response postResponse = given()
                .contentType(ContentType.JSON).body(object)
                .when()
                .post(url);
        return postResponse;
    }

    public static Response postWithParamAndAuth(String url, String username, String passwd, 
                                                                        String key, Object value) {
        Response postResponse = given()
                .auth()
                .basic(username, passwd)
                .param(key, value)
                .post(url)
                .then()
                .extract()
                .response();
        return postResponse;
    }

    public static Response postWithParam(String url, String key, String value) {
        Response postResponse = given()
                .header(new Header("content-type", "multipart/form-data"))
                .multiPart("photo", new File(value))
                .formParam("photo")
                .post(url)
                .then()
                .extract()
                .response();
        return postResponse;
    }

    public static ArrayList<String> getResult(ResultSet resultSet, String column) {
        try {
            ArrayList<String> result = new ArrayList<>();
            while (resultSet.next()) {
                String text = resultSet.getString(column);
                result.add(text);
            }
            return result;
        }catch (Exception e) {
            LOG.error(e);
            return null;
        }
    }
    
}
