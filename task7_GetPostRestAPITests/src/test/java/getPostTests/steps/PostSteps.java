package getPostTests.steps;

import apiUtils.JSONUtils;
import getPostTests.models.Posts;
import stringsUtils.RandomString;

import java.util.ArrayList;

public class PostSteps {
    private static String randomTitle, randomBody;
    private static ArrayList<String> postDataList;

    public static String createPost(Posts posts, int userId, int id) {
        randomTitle = RandomString.getRandomLatinString(20);
        randomBody = RandomString.getRandomLatinString(40);
        String jsonBody = createPostData(userId, id, randomTitle, randomBody);
        jsonBody = posts.createPost(jsonBody);
        return jsonBody;
    }

    private static String createPostData(int userId, int id, String randomTitle, String randomBody) {
        String[] data = {"title " + randomTitle, "body " + randomBody, "userId " + userId, "id " + id};
        return JSONUtils.createJSONString(data);
    }

    public static ArrayList<String> getPostInfo(int userId, int id, String title, String body) {
        postDataList = new ArrayList<>();
        postDataList.add("userId "+ userId);
        postDataList.add("id " + id);
        postDataList.add("title " + title);
        postDataList.add("body " + body);
        return postDataList;
    }

    public static ArrayList<String> getPostInfo(int userId) {
        postDataList = new ArrayList<>();
        postDataList.add("userId "+ userId);
        postDataList.add("title " + randomTitle);
        postDataList.add("body " + randomBody);
        return postDataList;
    }
}
