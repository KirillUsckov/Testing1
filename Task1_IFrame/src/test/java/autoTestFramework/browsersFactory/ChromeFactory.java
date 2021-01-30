package autoTestFramework.browsersFactory;

import autoTestFramework.browsersUtils.BrowserProperties;
import autoTestFramework.browsersUtils.WebDriverInitialization;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * The class is responsible for initializing the Chrome browser
 */

public class ChromeFactory implements BrowserFactory {

    public ChromeFactory() {
        WebDriverManager.chromedriver().setup();
    }

    @Override
    public WebDriver getBrowserDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }



}
