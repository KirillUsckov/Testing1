package unionReportingTests.models;


import browsersUtils.WebDriverExtensions;
import logger.Logger;

public class Cookie {
    private static final Logger LOG = new Logger(Cookie.class);
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
        LOG.info("Check that cookies value isn't null");
        return getCookie() != null;
    }

    public boolean isCookieDeleted() {
        return getCookie() == null;
    }

    public void deleteCookie() {
        LOG.info("Delete named cookie");
        webDriverExtensions.deleteNamedCookie(key);

    }

    public void addCookie() {
        LOG.info("Add cookie");
        webDriverExtensions.addCookie(key, value);
    }

    public void addCookie(String path) {
        LOG.info("Add cookie");
        webDriverExtensions.addCookie(key, value);
    }


    public org.openqa.selenium.Cookie getCookie() {
        LOG.info("Get named cookie");
        return webDriverExtensions.getNamedCookie(key);
    }


    public String getCookieValue() {
        LOG.info("Get cookie's value");
        return getCookie().getValue();
    }
}
