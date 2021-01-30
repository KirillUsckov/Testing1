package BrowsersFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxFactory implements BrowserFactory {
    private final String FOX_DRIVER = "webdriver.gecko.driver";
    private final String FOX_DRIVER_PATH = "src/main/resources/drivers/geckodriver.exe";
    private final String WINDOWS_FOX_DRIVER_PATH = "src/main/resources/drivers.Windows/geckodriver.exe";
    private final String LINUX_FOX_DRIVER_PATH = "src/main/resources/drivers.Linux/geckodriver";

    FireFoxFactory(int codeOS){
        if (codeOS == 1) {
            System.setProperty(FOX_DRIVER, WINDOWS_FOX_DRIVER_PATH);
        } else {
            System.setProperty(FOX_DRIVER, LINUX_FOX_DRIVER_PATH);
        }
    }
    @Override
    public WebDriver getBrowserDriver(){
        return WebDriverInitialization.getInstance(new FirefoxDriver());
    }
}
