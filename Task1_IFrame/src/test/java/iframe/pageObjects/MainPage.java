package iframe.pageObjects;

import autoTestFramework.elements.Label;
import autoTestFramework.pages.BasePage;

import org.apache.log4j.Logger;


public class MainPage extends BasePage {

    public static final String PANEL_XPATH = "//div[@class='mce-tinymce mce-container mce-panel']";
    public static final String H3_TEXT = "h3";

    private Logger logger;

    public MainPage(String locator, String siteUrl) {
        super(locator, siteUrl);
        logger = Logger.getLogger(MainPage.class);
    }

    /**
     * Default constructor.
     */
    public MainPage(String siteUrl) {
        super(PANEL_XPATH, siteUrl);
        logger = Logger.getLogger(MainPage.class);
    }

    public String getPagesText() {
        logger.info("Initialisation of the label element");
        Label titleOfSite = new Label(Label.getElementByTag(H3_TEXT));
        return titleOfSite.getElementText();
    }
}
