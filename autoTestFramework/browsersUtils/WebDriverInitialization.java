package browsersUtils;

import browsersMaker.BrowserMaker;
import browsersMaker.ChromeFactoryMaker;
import browsersMaker.FireFoxFactoryMaker;
import stringsUtils.XMLStringsReader;
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
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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

    public static void setBrowser() {
        BrowserMaker maker = null;
        switch (System.getProperty("browser")) {
            case CHROME_BROWSER:
                maker = new ChromeFactoryMaker();
                break;
            case FIRE_FOX_BROWSER:
                maker = new FireFoxFactoryMaker();
                break;
        }

        webDriver = getInstance(maker.createBrowser().getBrowserDriver());
    }


    public static void quiteBrowser() {
        webDriver.quit();
    }
}