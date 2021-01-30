import AutoTest.Elements.ButtonElement;
import AutoTest.Pages.PopupWindow;
import AutoTest.Pages.BasePage;
import AutoTest.StringsUtil.StringsReader;
import org.apache.log4j.Logger;

/**
 * Class is used to interact with the install page
 */
public class InstallPage extends BasePage {
    private final String SETUP_STEAM_BUTTON_NAME = "setupSteamButtonXPath";
    private final String ELEMENTS_XPATH_FILE_LINK = "src/main/resources/elementsXpath.xml";

    private StringsReader stringsReader;

    private final Logger logger;

    InstallPage() {
        super();
        logger = Logger.getLogger(InstallPage.class);
        stringsReader = new StringsReader(ELEMENTS_XPATH_FILE_LINK);
    }


    public void downloadSteamSetup() {
        logger.info("Searching for install Steam app button");
        ButtonElement installSteamButton = new ButtonElement();
        installSteamButton.findElement(stringsReader.getCustomElement(SETUP_STEAM_BUTTON_NAME));
        logger.info("Click install Steam button");
        installSteamButton.clickElement();

        PopupWindow popupWindow = new PopupWindow();
        logger.info("Setting download permission");
        popupWindow.popUpPressEnter();
    }
}
