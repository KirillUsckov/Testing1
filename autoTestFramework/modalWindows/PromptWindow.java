package modalWindows;

import enums.AlertActions;
import stringsUtils.RandomString;
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
        String text = RandomString.getRandomLatinString(20);
        sendTextToWindow(text);
        clickButton(AlertActions.ACCEPT);
        return text;
    }
}
