package BrowsersFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeFactory implements BrowserFactory {
    private final String CHROME_DRIVER = "webdriver.chrome.driver";
    private final String WINDOWS_CHROME_DRIVER_PATH = "src/main/resources/drivers.Windows/chromedriver.exe";
    private final String LINUX_CHROME_DRIVER_PATH = "src/main/resources/drivers.Linux/chromedriver";


    ChromeFactory(int codeOS){
        if (codeOS == 1) {
            System.setProperty(CHROME_DRIVER, WINDOWS_CHROME_DRIVER_PATH);
        } else {
            System.setProperty(CHROME_DRIVER,LINUX_CHROME_DRIVER_PATH);
        }
    }

    @Override
    public WebDriver getBrowserDriver(){

        return WebDriverInitialization.getInstance(new ChromeDriver());
    }
}
