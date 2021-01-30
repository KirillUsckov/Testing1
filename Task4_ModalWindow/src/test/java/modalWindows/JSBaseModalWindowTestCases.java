package modalWindows;

import autoTestFramework.BaseTest;
import autoTestFramework.enums.AlertActions;
import autoTestFramework.logger.Logger;
import autoTestFramework.modalWindows.AlertWindow;
import autoTestFramework.modalWindows.ConfirmWindow;
import autoTestFramework.modalWindows.PromptWindow;

import modalWindows.enums.ModalWindowsButtons;
import modalWindows.pageObjects.MainPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class JSBaseModalWindowTestCases extends BaseTest {
    private MainPage mainPage;
    private AlertWindow alertWindow;
    private ConfirmWindow confirmWindow;
    private PromptWindow promptWindow;

    private Logger logger;

    @BeforeClass
    public void initialisation() {
        mainPage = new MainPage(siteUrl);
        alertWindow = new AlertWindow();
        confirmWindow = new ConfirmWindow();
        promptWindow = new PromptWindow();
        logger = new Logger();
    }

    @Test
    public void checkModalWindowsResults() {
        mainPage.openPage();

        logger.step("Click JSAlert");
        mainPage.clickOnJSButton(ModalWindowsButtons.JS_ALERT_BUTTON.getButton());
        Assert.assertEquals(alertWindow.getText(), "I am a JS Alert");

        logger.step("Click OK in JSAlert");
        alertWindow.clickButton(AlertActions.ACCEPT);
        Assert.assertEquals(mainPage.getResult(), "You successfuly clicked an alert");

        logger.step("Click JSConfirm");
        mainPage.clickOnJSButton(ModalWindowsButtons.JS_CONFIRM_BUTTON.getButton());
        Assert.assertEquals(confirmWindow.getText(), "I am a JS Confirm");
        logger.step("Click OK in JSConfirm");
        confirmWindow.clickButton(AlertActions.ACCEPT);
        Assert.assertEquals(mainPage.getResult(),  "You clicked: Ok");

        logger.step("Click JSPrompt");
        mainPage.clickOnJSButton(ModalWindowsButtons.JS_PROMPT_BUTTON.getButton());
        logger.step("Enter random text in JSPrompt");
        enterRandomTextInPrompt();
    }

    private void enterRandomTextInPrompt() {
        Assert.assertEquals(promptWindow.getText(), "I am a JS prompt");
        String text = promptWindow.sendRandomTextToWindow();
        Assert.assertEquals(mainPage.getResult(), "You entered: " + text);
    }
    
}
