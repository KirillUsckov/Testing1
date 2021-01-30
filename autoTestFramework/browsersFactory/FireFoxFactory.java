package browsersFactory;

import browsersUtils.BrowserProperties;
import browsersUtils.WebDriverInitialization;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        WebDriver driver = new FirefoxDriver();
        return WebDriverInitialization.getInstance(driver);
    }
}
