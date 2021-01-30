package autoTestFramework.browsersUtils;

import autoTestFramework.browsersMaker.BrowserMaker;
import autoTestFramework.browsersMaker.ChromeFactoryMaker;
import autoTestFramework.browsersMaker.FireFoxFactoryMaker;
import autoTestFramework.stringsUtils.XMLStringsReader;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * The class is responsible for initializing BrowserMaker and get instance of driver
 */

public class WebDriverInitialization {
    private static final String CHROME_BROWSER = "Chrome";
    private static final String FIRE_FOX_BROWSER = "FireFox";
    private static WebDriver webDriver = null;
    private static WebDriverInitialization webDriverInitialization;

    private WebDriverInitialization(WebDriver driver) {
        webDriver = driver;
        webDriver.manage().window().maximize();
    }

    public static WebDriver getInstance(WebDriver driver) {
        if (webDriverInitialization == null) {
            webDriverInitialization = new WebDriverInitialization(driver);
        }

        return webDriver;
    }

    public static WebDriver getInstance() {
        return webDriver;
    }

    public static void setBrowser(String settingsFilePath) {
        XMLStringsReader reader = new XMLStringsReader(settingsFilePath);
        BrowserMaker maker = null;

        switch (reader.getCustomElement("browser")) {
            case CHROME_BROWSER:
                maker = new ChromeFactoryMaker();
                break;
            case FIRE_FOX_BROWSER:
                maker = new FireFoxFactoryMaker();
        }

        webDriver = getInstance(maker.createBrowser().getBrowserDriver());
        webDriver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
    }

    public static void quiteBrowser() {
        webDriver.quit();
    }
}