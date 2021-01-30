package modalWindows.pageObjects;

import autoTestFramework.elements.Button;
import autoTestFramework.elements.Label;
import autoTestFramework.logger.Logger;
import autoTestFramework.pages.BasePage;
import modalWindows.enums.ModalWindowsButtons;


public class MainPage extends BasePage {
    private static String JS_BUTTON_XPATH = "//button[@onclick=' ()']";
    private static String RESULT_LABEL_XPATH = "//p[@id='result']";

    private Logger logger;

    public MainPage(String url) {
        super(JS_BUTTON_XPATH.replace(" ", ModalWindowsButtons.JS_ALERT_BUTTON.getButton()), url);
        logger = new Logger(MainPage.class);
    }

    public void clickOnJSButton(String modalWindow) {
        logger.info("Initialisation of " + modalWindow + " button");
        Button alertButton =  new Button(JS_BUTTON_XPATH.replace(" ", modalWindow));

        logger.info("Click on jsAlert " + modalWindow + " button");
        alertButton.clickElement();
    }

    public String getResult(){
        logger.info("Initialisation of result label");
        Label result = new Label(RESULT_LABEL_XPATH);
        logger.info("Result: \"" + result.getElementText() + "\"");
        return result.getElementText();
    }

}
