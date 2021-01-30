package autoTestFramework.browsersFactory;

import autoTestFramework.browsersUtils.BrowserProperties;
import autoTestFramework.browsersUtils.WebDriverInitialization;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * The class is responsible for initializing the FireFox browser
 */

public class FireFoxFactory implements BrowserFactory {

    public FireFoxFactory() {
        WebDriverManager.firefoxdriver().setup();
    }

    @Override
    public WebDriver getBrowserDriver() {
        return WebDriverInitialization.getInstance(new FirefoxDriver(BrowserProperties.getFireFoxSettings()));
    }
}
