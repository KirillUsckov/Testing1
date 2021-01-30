package autoTestFramework.browsersUtils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class WebDriverExtensions {

    private WebDriver webDriver;

    public WebDriverExtensions() {
        webDriver = WebDriverInitialization.getInstance();
    }

    public void addCookie(String key, String value) {
        webDriver.manage().addCookie(new Cookie(key, value));
    }

    public Cookie getNamedCookie(String key) {
        return webDriver.manage().getCookieNamed(key);
    }

    public Set<Cookie> getAllCookie() {
        return webDriver.manage().getCookies();
    }

    public void deleteNamedCookie(String key) {
        webDriver.manage().deleteCookieNamed(key);
    }

    public void deleteAllCookies() {
        webDriver.manage().deleteAllCookies();
    }

}
