package userInterfaceTests.tests;

import baseTest.BaseTest;
import logger.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import userInterfaceTests.pageObjects.EnterEmailAndPasswordPage;
import userInterfaceTests.pageObjects.MainPage;
import userInterfaceTests.steps.TestSteps;

public class CookiesTestCases extends BaseTest {
    private MainPage mainPage;
    private EnterEmailAndPasswordPage registrationPage;
    private Logger logger;

    @BeforeClass
    public void initialization() {
        mainPage = new MainPage(siteUrl);
        registrationPage = new EnterEmailAndPasswordPage();
        logger = new Logger(CookiesTestCases.class);
        logger.testCase();
    }

    @Test
    public void acceptingCookiesTestCase() {
        logger.step("Open main page");
        TestSteps.openMainPage(mainPage);
        mainPage.clickOnStartingGameLabel();

        logger.step("Accept cookie");
        registrationPage.clickOnAcceptCookies();
        Assert.assertFalse(registrationPage.isCookieFormEnable());
    }
}
