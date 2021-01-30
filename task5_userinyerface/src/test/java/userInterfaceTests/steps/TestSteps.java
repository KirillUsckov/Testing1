package userInterfaceTests.steps;

import org.testng.Assert;
import userInterfaceTests.pageObjects.MainPage;

public class TestSteps {

    public static void openMainPage(MainPage mainPage) {
        mainPage.openPage();
        Assert.assertTrue(mainPage.isPageOpen());
    }
}
