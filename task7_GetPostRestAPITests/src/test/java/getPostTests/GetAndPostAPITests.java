package getPostTests;

import apiUtils.JSONUtils;
import baseTest.BaseAPITest;
import getPostTests.models.Posts;

import getPostTests.models.Users;
import getPostTests.steps.PostSteps;
import getPostTests.steps.UserSteps;
import logger.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetAndPostAPITests extends BaseAPITest {



    private Posts posts;
    private Users users;
    private Logger logger;

    @DataProvider(name = "GET and POST")
    public Object[][] setData() {
        return new Object[][] {{99, 10, 150, 1, 101, "5"}};
    }

    @BeforeClass
    public void initialisation() {
        logger = new Logger(GetAndPostAPITests.class);
        logger.info("Initialisation of posts object");
        posts = new Posts(siteUrl);
        users = new Users(siteUrl);
    }

    @Test(dataProvider = "GET and POST")
    public void getAndPostTests(int scndStepPostId, int scndStepUserId, int thrdStepPostId, int frthUserId,
                                                                int ftrhId, String fiveSixStepUserId) {

        logger.step("Get query to get all posts");
        UserSteps.getQueryToGetAllPosts(posts);

        logger.step("Get query to get post with id = 99");
        UserSteps.getQueryToGetValidPost(posts,scndStepPostId, scndStepUserId);

        logger.step("Get query to get post with id = 150");
        UserSteps.getQueryToGetNonValidPost(posts,thrdStepPostId);

        logger.step("Post query to create post");
        UserSteps.postQueryToCreatePost(posts, frthUserId, ftrhId);

        logger.step("Get query to get users");
        UserSteps.getQueryToGetAllUsersAnFindUserById(users, fiveSixStepUserId);

        logger.step("Get query to get 5th user");
        UserSteps.getUserById(users, fiveSixStepUserId);
    }


}
