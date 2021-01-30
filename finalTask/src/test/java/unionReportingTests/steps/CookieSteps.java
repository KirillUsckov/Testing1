package unionReportingTests.steps;

import unionReportingTests.models.Cookie;
import unionReportingTests.pageObjects.MainPage;

public class CookieSteps {

    private CookieSteps() {}

    public static void addCookieToken(MainPage mainPage, String token) {
        Cookie cookie = new Cookie("token", token);
        cookie.addCookie();
        mainPage.reloadPage();
    }
}
