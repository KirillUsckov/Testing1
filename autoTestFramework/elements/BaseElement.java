package elements;

import browsersUtils.WebDriverInitialization;
import logger.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The class is responsible for initializing the BaseElement and for
 * implementing the basic element functionality
 */

public abstract class BaseElement {

    private static final Logger LOG = new Logger(BaseElement.class);
    private final int WAITING_TIME = 5;
    protected String locator;
    protected String name;
    protected WebElement element;
    protected static WebDriver webDriver;

    public BaseElement(String xPath, String name) {
        LOG.info("Initialization of element");
        webDriver = WebDriverInitialization.getInstance();
        this.name = name;
        this.locator = xPath;
    }

    public BaseElement(WebElement webElement, String name) {
        LOG.info("Initialization of element");
        webDriver = WebDriverInitialization.getInstance();
        this.name = name;
        setElement(webElement);
    }

    public static WebElement getElementByTag(String tagName) {
        webDriver = WebDriverInitialization.getInstance();
        return webDriver.findElement(By.tagName(tagName));
    }

    public void setElement(WebElement webElement) {
        LOG.info("Initialization of element");
        element = webElement;
    }

    public void clickElement() {
        try {
            LOG.info("Click on element");
            findElement();
            element.click();
        } catch (Exception e) {
            LOG.error("ELEMENT " + name + ":" + e);
        }
    }

    public String getElementText() {
        LOG.info("Get element's text");
        findElement();
        return element.getText();
    }

    public String getHref() {
        LOG.info("Get element's href attribute");
        findElement();
        return element.getAttribute("href");
    }

    public String getElementAttributeValue(String key) {
        LOG.info("Get element's attribute value");
        try {
            findElement();
            return element.getAttribute(key);
        } catch (Exception e) {
            LOG.error("ELEMENT " + name + ":" + e);
            return null;
        }
    }

    public Boolean isElementDisplayed() {
        LOG.info("Return element's display statement value");
        findElement();
        return element.isDisplayed();
    }

    public void findElement() {
        LOG.info("Try to find element");
        try {
            if (element == null) {
                element = new WebDriverWait(webDriver, WAITING_TIME)
                        .until(webDriver -> webDriver.findElement(By.xpath(locator)));
            }
        } catch (Exception e) {
            LOG.error("ELEMENT " + name + ":" + e);
        }
    }

    public void findElementByTagName(String tag) {
        try {
            element = webDriver.findElement(By.tagName(tag));
        } catch (Exception e) {
            LOG.error("ELEMENT " + name + ":" + e);
        }
    }

    public WebElement findChildElement(String xPath) {
        LOG.info("Try to find element's child element");
        return element.findElement(By.xpath(xPath));
    }

    public boolean isElementNull() {
        return element.equals(null);
    }
}