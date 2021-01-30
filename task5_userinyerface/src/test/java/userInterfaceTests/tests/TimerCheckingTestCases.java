package userInterfaceTests.tests;

import baseTest.BaseTest;
import logger.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import userInterfaceTests.pageObjects.EnterEmailAndPasswordPage;
import userInterfaceTests.pageObjects.MainPage;
import userInterfaceTests.steps.TestSteps;

public class TimerCheckingTestCases extends BaseTest {
    private MainPage mainPage;
    private EnterEmailAndPasswordPage registrationPage;
    private Logger logger;


    @BeforeClass
    public void initialization() {
        mainPage = new MainPage(siteUrl);
        registrationPage = new EnterEmailAndPasswordPage();
        logger = new Logger(TimerCheckingTestCases.class);
        logger.testCase();
    }

    @Test
    public void checkingTimeTestCase() {
        logger.step("Open main page");
        TestSteps.openMainPage(mainPage);
        mainPage.clickOnStartingGameLabel();

        logger.step("Checking that timer starts from 0");
        Assert.assertTrue(registrationPage.isTimerStartsFromZero("00:00:00"));
    }
}
