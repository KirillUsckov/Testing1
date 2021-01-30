package cookiesTest.steps;

import autoTestFramework.browsersUtils.WebDriverExtensions;
import cookiesTest.models.Cookie;
import org.apache.log4j.Logger;

import java.util.HashMap;

public class Steps {

    private static WebDriverExtensions webDriverExtensions = new WebDriverExtensions();
    private static Logger logger = Logger.getLogger(Steps.class);
    private static Cookie cookie;
    private static boolean result = true;

    public static void addAllCookies(HashMap testCookies) {
        logger.info("Add all cookies");
        for (int i = 0; i < testCookies.size(); i++) {
            cookie = (Cookie) testCookies.get(i);
            cookie.addCookie();
            isAllCookiesAdded();
        }
    }

    public static boolean isAllCookiesAdded() {
        result = result && cookie.isCookieNotNull();
        return result;
    }

    public static String getAddedCookieValue(Cookie inputCookie) {
        inputCookie.addCookie();
        return inputCookie.getCookieValue();
    }

    public static boolean isCookieDeleted(Cookie inputCookie) {
        inputCookie.deleteCookie();
        return inputCookie.isCookieDeleted();
    }

    public static void deleteAllCookies() {
        logger.info("Delete all cookies");
        webDriverExtensions.deleteAllCookies();
    }

    public static boolean isCookieMissing() {
        logger.info("Get number of files");
        return webDriverExtensions.getAllCookie().size() == 0;
    }
}
