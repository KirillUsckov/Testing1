package cookiesTest;

import autoTestFramework.BaseTest;

import cookiesTest.constants.TestData;
import cookiesTest.models.Cookie;
import cookiesTest.pageObjects.MainPage;
import cookiesTest.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

public class CookieTestCases extends BaseTest {
    private MainPage mainPage;

    private HashMap<Integer, Cookie> testCookies = new HashMap();

    @BeforeClass
    public void initialising() {
        mainPage = new MainPage(siteUrl);
        testCookies.put(0, new Cookie(TestData.KEY_1, TestData.VALUE_1));
        testCookies.put(1, new Cookie(TestData.KEY_2, TestData.VALUE_2));
        testCookies.put(2, new Cookie(TestData.KEY_3, TestData.VALUE_3));
    }

    @Test
    public void editCookieTest() {
        mainPage.openPage();
        Steps.addAllCookies(testCookies);
        Assert.assertTrue(Steps.isAllCookiesAdded());

        Cookie cookie = new Cookie(TestData.KEY_1);
        Assert.assertTrue(Steps.isCookieDeleted(cookie));

        cookie = new Cookie(TestData.KEY_3, TestData.VALUE_300);
        Assert.assertEquals(Steps.getAddedCookieValue(cookie), TestData.VALUE_300);

        Steps.deleteAllCookies();
        Assert.assertTrue(Steps.isCookieMissing());
    }
}