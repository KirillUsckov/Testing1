package getPostTests.models;

import apiUtils.APIUtils;
import apiUtils.JSONUtils;

import enums.APIResponses;
import getPostTests.enums.APIResources;
import io.restassured.response.Response;
import logger.Logger;

import java.util.ArrayList;

public class Posts {
    private final static String MAP_TEXT = "\"map\":";

    private String siteUrl;
    private Response response;
    private int issues = 0;
    private Logger logger;

    public Posts(String url) {
        logger = new Logger(Posts.class);
        siteUrl = url;
    }

    public String getAllPosts() {
        logger.info("Send GET query to get all posts and check that STATUS CODE is 200");
        response = APIUtils.getWithCodeAssert(siteUrl + APIResources.ALL_POSTS.get(), 200);

        logger.info("Return GET response body text");
        return response.asString();
    }

    public String getCustomPost(String postId) {
        logger.info("Send get query to get custom post");
        response = APIUtils.get(siteUrl + APIResources.CUSTOM_POST.get() + postId);

        logger.info("Return GET response body text");
        return response.asString();
    }

    public int getLastResponseCode() {
        logger.info("Return STATUS CODE");
        return response.getStatusCode();
    }

    public boolean isPostsSorted() {
        logger.info("Return result of checking that response elements is sorted");
        return JSONUtils.isJSONFileSorted("id", JSONUtils.getCorrectJSON(response));
    }

    public String createPost(String jsonBody) {
        response = APIUtils.query(APIResponses.POST, jsonBody, siteUrl + APIResources.ALL_POSTS.get());
        return response.asString();
    }

    public boolean isResponseHasJSONFormat() {
        logger.info("Return result of checking that response has JSON format");
        return JSONUtils.isJSON(JSONUtils.getCorrectJSON(response));
    }

    public boolean isKeyValueOfPostCorrect(String post, String key, String value) {
        logger.info("Return result of checking that key value of JSON is correct");
        return JSONUtils.checkKeyValue(post, key, value);
    }

    public boolean isAllPostDataCorrect(ArrayList<String> postData, String post) {
        logger.info("Return result of checking that all JSON body has correct data");
        return JSONUtils.checkArrayValues(postData, post);
    }

    public String getNewPostData(String post) {
        try {
            logger.info("Get post text from response");
            int startIndex = post.indexOf(MAP_TEXT) + MAP_TEXT.length();
            String textOfPost = post.substring(startIndex, post.lastIndexOf(","));
            return textOfPost;
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

}
