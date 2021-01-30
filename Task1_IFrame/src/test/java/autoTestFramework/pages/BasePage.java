package autoTestFramework.pages;

import autoTestFramework.browsersUtils.BrowserProperties;
import autoTestFramework.browsersUtils.WebDriverInitialization;
import autoTestFramework.elements.Img;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * The class is responsible for initializing the BasePage and for
 * implementing the basic page functionality
 */

public abstract class BasePage {
    protected WebDriver webDriver;
    private String url;
    private String locator;

    public BasePage(String locator) {
        this.locator = locator;
        webDriver = WebDriverInitialization.getInstance();
    }

    public BasePage(String locator, String siteUrl) {
        this.locator = locator;
        setUrl(siteUrl);
        webDriver = WebDriverInitialization.getInstance();
    }

    public BasePage() {
        webDriver = WebDriverInitialization.getInstance();
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public void reloadPage() {
        webDriver.navigate().refresh();
    }

    public List<WebElement> findPageElements(String xPath) {
        return webDriver.findElements(By.xpath(xPath));
    }

    public void setUrl(String siteUrl) {
        url = siteUrl;
    }

    public void waitingForOpen() {
        Img imgElement = new Img(locator);
        imgElement.findElement();
        while(imgElement.isElementDisplayed()) {
            imgElement.findElement();
        }
    }

    public String getLocalisation() {
        return webDriver.findElement(By.xpath("html")).getAttribute("lang");
    }

    public boolean isPageOpen() {
        WebElement element =  new WebDriverWait(webDriver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        return !element.equals(null);
    }

    public void openPage() {
        webDriver.get(url);
    }

    public void localisation() {
        switch (BrowserProperties.getLocalisation()) {
            case "by":
                url = url.substring(0,url.lastIndexOf("."));
                url += "by";
                break;
        }
    }

    public void closePage() {
        webDriver.close();
    }

}