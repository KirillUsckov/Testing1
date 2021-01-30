package cookiesTest.models;

import autoTestFramework.browsersUtils.WebDriverExtensions;
import org.apache.log4j.Logger;

public class Cookie {
    private static Logger logger = Logger.getLogger(Cookie.class);
    private static WebDriverExtensions webDriverExtensions = new WebDriverExtensions();
    private String key;
    private String value;

    public Cookie(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Cookie(String key) {
        this.key = key;
    }

    public boolean isCookieNotNull() {
        logger.info("Check that cookies value isn't null");
        return getCookie() != null;
    }

    public boolean isCookieDeleted() {
        return getCookie() == null;
    }

    public void deleteCookie() {
        logger.info("Delete named cookie");
        webDriverExtensions.deleteNamedCookie(key);

    }

    public void addCookie() {
        logger.info("Add cookie");
        webDriverExtensions.addCookie(key, value);
    }

    public org.openqa.selenium.Cookie getCookie() {
        logger.info("Get named cookie");
        return webDriverExtensions.getNamedCookie(key);
    }


    public String getCookieValue() {
        logger.info("Get cookie's value");
        return getCookie().getValue();
    }
}
