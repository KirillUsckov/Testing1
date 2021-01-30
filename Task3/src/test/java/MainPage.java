import AutoTest.Elements.ButtonElement;
import AutoTest.Pages.BasePage;
import AutoTest.StringsUtil.StringsReader;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Class is used to interact with the main page
 */
public class MainPage extends BasePage {
    private final String SETTINGS_LINK = "src/main/resources/settings.xml";
    private final String ELEMENTS_XPATH_FILE_LINK = "src/main/resources/elementsXpath.xml";

    private final String SITE_URL = "siteUrl";
    private final String LOCALISATION = "localisation";

    private final String EN = "en";
    private final String RU = "ru";
    private final String ENGLISH = "English";
    private final String RUSSIAN = "Russian";

    private final String INSTALL_BUTTON_NAME = "installButtonXpath";
    private final String LANGUAGE_LIST = "languagesListXpath";
    private final String LOCALISATION_BUTTON = "localisationSetterXpath";

    private String language;

    private final Logger logger;

    private StringsReader stringsReader;

    MainPage(){
        super();
        logger = Logger.getLogger(MainPage.class);
        stringsReader = new StringsReader(SETTINGS_LINK);
        setUrl(stringsReader.getCustomElement(SITE_URL));

        language = stringsReader.getCustomElement(LOCALISATION);
        stringsReader = new StringsReader(ELEMENTS_XPATH_FILE_LINK);
    }

    public void goToInstallPage() {
        logger.info("Searching for the install button on the main page.");
        ButtonElement installButton = new ButtonElement();
        installButton.findElement(stringsReader.getCustomElement(INSTALL_BUTTON_NAME));

        logger.info("Click install button on the main page.");
        installButton.clickElement();

    }

    public void goToSelectGames(String genre) {
        logger.info("Searching for the genre button on the main page");
        ButtonElement selectedGenreButton = new ButtonElement();
        String elementXpath = "//a[@class='gutter_item'][contains(text(),\'" + genre + "\')]";
        selectedGenreButton.findElement(elementXpath);
        logger.info("Click on genre button");
        logger.info("Go to " + genre + " genre page");
        selectedGenreButton.clickElement();
    }

    public void setLocalisation() {
        logger.info("Searching for localisation button");
        ButtonElement localisationButton = new ButtonElement();
        localisationButton.findElement(stringsReader.getCustomElement(LOCALISATION_BUTTON));
        localisationButton.clickElement();

        switch (getLocalisation()){
            case EN:
                if (!language.contains(ENGLISH)) {
                    setLanguage();
                }
                break;
            case RU:
                if (!language.contains(RUSSIAN)) {
                    setLanguage();
                }
                break;
        }
    }

    private void setLanguage() {
        logger.info("Set " + language + " language");
        List<WebElement> languageList = findPageElements(stringsReader.getCustomElement(LANGUAGE_LIST));
        for (WebElement languageLable: languageList) {
            if(languageLable.getText().contains(language)) {
                languageLable.click();
            }
        }
    }
}
