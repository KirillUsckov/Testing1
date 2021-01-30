package elements;

import logger.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * The class is responsible for initializing the TextBoxElement and for
 * implementing the unique element functionality
 */


public class TextBox extends BaseElement{
    private static final Logger LOG = new Logger(TextBox.class);

    public TextBox(String locator,String name) {
        super(locator, name);
    }

    public TextBox(WebElement element, String name) {
        super(element, name);
    }

    public void clearAndSetText(String text) {
        LOG.info("Clear textBox and set value");
        findElement();
        while (!element.isDisplayed()) {
        }
        deleteAllText();
        setElementText(text);
    }

    public void sendTextByTagType(String tagName, String text) {
        LOG.info("Find textBox by tagName, set value");
        webDriver.findElement(By.tagName(tagName)).sendKeys(text);
    }

    public void setElementText(String text) {
        LOG.info("Set textBox's text");
        findElement();
        element.sendKeys(text);
    }

    public void deleteAllText() {
        LOG.info("Trying to remove text from textBox");
        try {
            clickElement();

            selectAllText();

            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DELETE);
            robot.keyRelease(KeyEvent.VK_DELETE);
        } catch (AWTException e) {
            LOG.error("ELEMENT " + name + ":" + e);
        }
    }

    public void selectAllText() {
        LOG.info("Trying to select textBox's text");
        try {
            Robot robot = new Robot();

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_A);

            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_A);
        } catch (AWTException e) {
            LOG.error("ELEMENT " + name + ":" + e);
        }
    }

}
