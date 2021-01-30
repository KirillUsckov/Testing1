package BrowsersFactory;

import org.openqa.selenium.WebDriver;

public class WebDriverInitialization {
    private static WebDriver webDriver;
    private WebDriverInitialization(WebDriver driver) {
        webDriver = driver;
    }

    public static WebDriver getInstance(WebDriver driver) {

        if (webDriver == null) {
            new WebDriverInitialization(driver);
        }
        //ход теста отображался в полностью открытом окне
        webDriver.manage().window().maximize();
        return webDriver;

    }

    public static WebDriver getInstance() {

        return webDriver;
    }
}
