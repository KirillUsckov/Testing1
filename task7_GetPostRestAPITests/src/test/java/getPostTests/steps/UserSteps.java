package getPostTests.steps;

import apiUtils.JSONUtils;
import getPostTests.constants.CommonConstants;
import getPostTests.models.Posts;
import getPostTests.models.Users;
import org.testng.Assert;
import stringsUtils.XMLStringsReader;

import java.util.ArrayList;

public class UserSteps {
    private static ArrayList<String> userData;
    private static XMLStringsReader reader = new XMLStringsReader(CommonConstants.TEST_USER_FILE_PATH);

    public static void getQueryToGetAllPosts(Posts posts) {
        posts.getAllPosts();
        Assert.assertTrue(posts.isResponseHasJSONFormat());
        Assert.assertTrue(posts.isPostsSorted());
    }

    public static String getQueryToGetValidPost(Posts posts, int postId, int userId) {
        String post = posts.getCustomPost(String.valueOf(postId));
        Assert.assertEquals(posts.getLastResponseCode(), 200);
        Assert.assertTrue(posts.isAllPostDataCorrect(PostSteps.getPostInfo(userId, postId,
                CommonConstants.NOT_NULL, CommonConstants.NOT_NULL), post));
        return post;
    }

    public static String getQueryToGetNonValidPost(Posts posts, int postId) {
        String post = posts.getCustomPost(String.valueOf(postId));
        Assert.assertEquals(posts.getLastResponseCode(), 404);
        Assert.assertTrue(JSONUtils.isBodyOfJSONEmpty(post));
        return post;
    }

    public static void postQueryToCreatePost(Posts posts, int userId, int id) {
        String jsonBody = PostSteps.createPost(posts, userId, id);

        Assert.assertEquals(posts.getLastResponseCode(), 201);
        Assert.assertTrue(posts.isKeyValueOfPostCorrect(jsonBody, "id", String.valueOf(id)));
        String post = posts.getNewPostData(jsonBody);
        Assert.assertTrue(posts.isAllPostDataCorrect(PostSteps.getPostInfo(userId), post));
    }

    public static void getQueryToGetAllUsersAnFindUserById(Users users, String id) {
        String usersList = users.getAllUsers();
        Assert.assertTrue(users.responseCodeChecking(200));
        Assert.assertTrue(JSONUtils.isJSON(usersList));
        String user = users.findUserById(id);
        Assert.assertTrue(users.checkUserData(user, UserSteps.getCmnData(), UserSteps.getAdrsData(), UserSteps.getCmpnyData()));
    }
    public static void getUserById(Users users, String id) {
        String user = users.getUserById(id);
        Assert.assertTrue(users.responseCodeChecking(200));
        Assert.assertTrue(users.checkUserData(user, UserSteps.getCmnData(), UserSteps.getAdrsData(), UserSteps.getCmpnyData()));
    }


    public static ArrayList<String> getCmnData() {
        userData = new ArrayList<>();
        userData.add("name "+ reader.getCustomElement("name"));
        userData.add("username " + reader.getCustomElement("userName"));
        userData.add("email " + reader.getCustomElement("email"));
        userData.add("phone " + reader.getCustomElement("phone"));
        userData.add("website " + reader.getCustomElement("website"));
        return userData;
    }

    public static ArrayList<String> getAdrsData() {
        userData = new ArrayList<>();
        userData.add("street "+ reader.getCustomElement("street"));
        userData.add("suite " + reader.getCustomElement("suite"));
        userData.add("city " + reader.getCustomElement("city"));
        userData.add("zipcode " + reader.getCustomElement("zipcode"));
        String[] geo = {"lat " + reader.getCustomElement("lat"),
                            "lng " + reader.getCustomElement("lng")};
        userData.add("geo " + JSONUtils.createJSONString(geo));

        return userData;
    }

    public static ArrayList<String> getCmpnyData() {
        userData = new ArrayList<>();
        userData.add("name "+ reader.getCustomElement("companyName"));
        userData.add("catchPhrase " + reader.getCustomElement("catchPhrase"));
        userData.add("bs " + reader.getCustomElement("bs"));
        return userData;
    }

}
