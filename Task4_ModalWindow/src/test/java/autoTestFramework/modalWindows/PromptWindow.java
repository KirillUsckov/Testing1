package autoTestFramework.modalWindows;

import autoTestFramework.enums.AlertActions;
import autoTestFramework.stringsUtils.RandomString;
import org.openqa.selenium.Alert;

public class PromptWindow extends BaseModalWindow {

    public PromptWindow() {
        super();
    }

    public void sendTextToWindow(String text) {
        logger.info("Set text to prompt");
        Alert alert = webDriver.switchTo().alert();
        alert.sendKeys(text);
    }

    public String sendRandomTextToWindow() {
        String text = RandomString.getRandomString(20);
        sendTextToWindow(text);
        clickButton(AlertActions.ACCEPT);
        return text;
    }
}
