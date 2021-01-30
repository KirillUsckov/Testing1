package userInterfaceTests.tests;

import baseTest.BaseTest;
import logger.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import userInterfaceTests.pageObjects.EnterEmailAndPasswordPage;
import userInterfaceTests.pageObjects.InterestsAndAvatarPage;
import userInterfaceTests.pageObjects.MainPage;
import userInterfaceTests.pageObjects.PersonalDetailsPage;
import userInterfaceTests.steps.TestSteps;

public class FormsTestCases extends BaseTest {

    private MainPage mainPage;
    private EnterEmailAndPasswordPage registrationPage;
    private InterestsAndAvatarPage interestsAndAvatarPage;
    private PersonalDetailsPage personalDetailsPage;
    private Logger logger;
    private String avatarLocation;

    @BeforeClass
    public void initialization() {
        mainPage = new MainPage(siteUrl);
        registrationPage = new EnterEmailAndPasswordPage();
        interestsAndAvatarPage = new InterestsAndAvatarPage();
        personalDetailsPage = new PersonalDetailsPage();

        avatarLocation = strReader.getCustomElement("imgLocation");

        logger = new Logger(FormsTestCases.class);
        logger.testCase();
    }

    @Test
    public void formsChecking() {
        logger.step("Open main page");
        TestSteps.openMainPage(mainPage);
        logger.step("Start the game");
        mainPage.clickOnStartingGameLabel();
        Assert.assertTrue(registrationPage.isPageOpen());

        logger.step("Enter random data");
        registrationPage.enterRandomPasswordAndEmail();
        registrationPage.clickOnAcceptingTermsCheckBox();
        registrationPage.clickOnNextButton();
        Assert.assertTrue(interestsAndAvatarPage.isPageOpen());

        logger.step("Set 3 random interests and avatar");
        interestsAndAvatarPage.clickOnUploadLabel();
        interestsAndAvatarPage.uploadAvatar(avatarLocation);
        interestsAndAvatarPage.setInterests(3);
        interestsAndAvatarPage.clickNextButton();

        Assert.assertTrue(personalDetailsPage.isPersonalDetailsPageOpen("3 / 4"));
    }
}
