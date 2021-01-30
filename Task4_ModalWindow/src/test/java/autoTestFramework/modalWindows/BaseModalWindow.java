package autoTestFramework.modalWindows;

import autoTestFramework.browsersUtils.WebDriverInitialization;
import autoTestFramework.enums.AlertActions;
import autoTestFramework.logger.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class BaseModalWindow {
    protected WebDriver webDriver;
    protected Logger logger;

    public BaseModalWindow() {
        logger = new Logger(BaseModalWindow.class);
        webDriver = WebDriverInitialization.getInstance();
    }

    public void clickButton(AlertActions alertAction) {
        try {
            Alert alert = webDriver.switchTo().alert();
            if (alertAction.equals(AlertActions.ACCEPT)) {
                logger.info("Click accept");
                alert.accept();
            } else {
                logger.info("Click decline");
                alert.dismiss();
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    public String getText() {
        Alert alert = webDriver.switchTo().alert();
        return alert.getText();
    }


}
