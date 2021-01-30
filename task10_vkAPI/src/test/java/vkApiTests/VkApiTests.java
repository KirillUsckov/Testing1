package vkApiTests;

import vkApiTests.steps.ApiSteps;
import vkApiTests.vkApi.VkApiUtils;
import baseTest.BaseTest;

import vkApiTests.contsants.CommonConstants;
import logger.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import vkApiTests.pageObjects.FeedPage;
import vkApiTests.pageObjects.LoginPage;
import vkApiTests.pageObjects.ProfilePage;
import stringsUtils.XMLStringsReader;

public class VkApiTests extends BaseTest {
    public static final String PHOTO_PATH = "/src/test/resources/test.png";
    private Logger logger = new Logger(VkApiTests.class);
    private LoginPage loginPage;
    private FeedPage feedPage;
    private ProfilePage profilePage;
    private String email;
    private String passwd;
    private VkApiUtils api;

    @BeforeTest
    public void initPagesAndTestData() {
        loginPage = new LoginPage(siteUrl);
        feedPage = new FeedPage();
        profilePage = new ProfilePage("//div[@id='profile']");
        XMLStringsReader reader = new XMLStringsReader(CommonConstants.TEST_USER_DATA_PATH);
        email = reader.getCustomElement("email");
        passwd = reader.getCustomElement("password");
        String token = reader.getCustomElement("token");
        api = new VkApiUtils(token);
    }

    @Test
    public void testCase() {
        ApiSteps steps = new ApiSteps(profilePage, api);

        logger.step("Open login page");
        loginPage.openPage();

        logger.step("Login");
        loginPage.enterEmail(email);
        loginPage.enterPassword(passwd);
        loginPage.clickLoginButton();

        logger.step("Open profile page");
        feedPage.waitingForOpen();
        feedPage.clickProfileMenuButton();

        logger.step("Set API request to add a post");
        steps.addPost();


        logger.step("Edit post text and add image attachment");
        steps.addRandomTextAndPhotoToPost(PHOTO_PATH);

        logger.step("Add comment to a post");
        steps.addRandomCommentToPost();

        logger.step("Like post");
        profilePage.clickOnLike(steps.getPost());
        Assert.assertTrue(api.isPostLiked(steps.getPost()));

        logger.step("Delete post");
        api.deletePost(steps.getPost());
    }
}
