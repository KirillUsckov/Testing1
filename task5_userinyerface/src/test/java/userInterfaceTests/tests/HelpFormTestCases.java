package userInterfaceTests.tests;

import baseTest.BaseTest;
import logger.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import userInterfaceTests.pageObjects.EnterEmailAndPasswordPage;
import userInterfaceTests.pageObjects.MainPage;
import userInterfaceTests.steps.TestSteps;

public class HelpFormTestCases extends BaseTest {
    private MainPage mainPage;
    private EnterEmailAndPasswordPage enterEmailAndPasswordPage;
    private Logger logger;

    @BeforeClass
    public void initialization() {
        mainPage = new MainPage(siteUrl);
        enterEmailAndPasswordPage = new EnterEmailAndPasswordPage();
        logger = new Logger(HelpFormTestCases.class);
        logger.testCase();
    }

    @Test
    public void openHelpPageChecking() {
        logger.step("Open main page");
        TestSteps.openMainPage(mainPage);
        mainPage.clickOnStartingGameLabel();

        logger.step("Hide help form");
        enterEmailAndPasswordPage.clickOnHideHelpFormButton();
        Assert.assertFalse(enterEmailAndPasswordPage.isHelpFormEnable());
    }
}
