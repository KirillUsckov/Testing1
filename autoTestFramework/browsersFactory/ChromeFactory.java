package browsersFactory;

import browsersUtils.BrowserProperties;
import browsersUtils.WebDriverInitialization;
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
        WebDriver driver = new ChromeDriver(BrowserProperties.getChromeSettings());
        return WebDriverInitialization.getInstance(driver);
    }



}
