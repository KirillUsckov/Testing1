package iframe.pageObjects;

import autoTestFramework.elements.Button;
import autoTestFramework.elements.TextBox;
import autoTestFramework.elementsUtils.ElementsList;
import autoTestFramework.pages.Frame;
import org.apache.log4j.Logger;
import iframe.projectUtils.RandomString;

public class IFrame extends Frame {
    public static final String IFRAME_XPATH = "//iframe[@id='mce_0_ifr']";
    public static final String BOLD_BUTTON_XPATH = "//i[@class='mce-ico mce-i-bold']";
    public static final String STRONG_DIV_XPATH = "//div[@id='mceu_29-1' and contains(text(), 'strong')]";

    public static final String BODY_TEXT = "body";

    private final int RANDOM_TEXT_LENGTH = 30;

    private Logger logger;

    private TextBox iframeInputTextBox;

    public IFrame() {
        super(IFRAME_XPATH);
        logger = Logger.getLogger(IFrame.class);
    }

    public Boolean isStringBold() {
        logger.info("Initialisation of the label elements list");
        ElementsList labelElements = new ElementsList(STRONG_DIV_XPATH);
        return labelElements.getElementsListSize() != 0;
    }

    public void deleteAllText() {
        logger.info("Initialisation of the textBox on the main page");
        iframeInputTextBox = new TextBox(IFRAME_XPATH);
        logger.info("Delete all text from textBox on the main page");
        iframeInputTextBox.deleteAllText();
    }

    public String setRandomText() {
        logger.info("Initialisation of the textBox on the main page");
        iframeInputTextBox = new TextBox(IFRAME_XPATH);
        logger.info("Click on the textBox on the main page");
        iframeInputTextBox.clickElement();
        logger.info("Creating random text");
        String text = RandomString.getRandomString(RANDOM_TEXT_LENGTH);

        logger.info("Initialisation of the frame on the main page");

        logger.info("Switch to frame");
        switchToFrame();
        logger.info("Send text: " + text);
        iframeInputTextBox.sendTextByTagType(BODY_TEXT, text);
        logger.info("Switch to default view");
        switchToDefault();

        return iframeInputTextBox.getElementText();
    }

    public void boldButtonClick() {
        logger.info("Initialisation of the boldButton on the main page");
        Button boldButton = new Button(BOLD_BUTTON_XPATH);
        logger.info("Initialisation of the textBox on the main page");
        iframeInputTextBox = new TextBox(IFRAME_XPATH);

        logger.info("Select all text on the textBox");
        iframeInputTextBox.selectAllText();
        logger.info("Click on the boldButton");
        boldButton.clickElement();
        logger.info("Click on the textBox");
        iframeInputTextBox.clickElement();
    }
}
