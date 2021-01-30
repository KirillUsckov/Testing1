package autoTestFramework.elements;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * The class is responsible for initializing the TextBoxElement and for
 * implementing the unique element functionality
 */


public class TextBox extends BaseElement{
    private Logger logger;

    public TextBox(String locator) {
        super(locator);
        logger = Logger.getLogger(TextBox.class);
    }

    public TextBox(WebElement element) {
        super(element);
        logger = Logger.getLogger(TextBox.class);
    }

    public void setText(String text) {
        while (!element.isDisplayed()) {
        }
        deleteAllText();
        sendText(text);
    }

    public void sendTextByTagType(String tagName, String text) {
        webDriver.findElement(By.tagName(tagName)).sendKeys(text);
    }

    public void sendText(String text) {
        element.sendKeys(text);
    }

    public void deleteAllText() {
        try {
            findElement();
            clickElement();

            selectAllText();

            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DELETE);
            robot.keyRelease(KeyEvent.VK_DELETE);
        } catch (AWTException e) {
            logger.warn(e);
        }
    }

    public void selectAllText() {
        try {
            Robot robot = new Robot();

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_A);

            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_A);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

}
