package waitingUtils;

import browsersUtils.WebDriverInitialization;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Waiting class is used for set waiting
 */

public class Waiting {
    public static void waitFiveSeconds() {
        WebDriver webDriver = WebDriverInitialization.getInstance();
        webDriver.manage() ;
    }

    public static void waitForOpenPage(WebDriver driver) {
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
