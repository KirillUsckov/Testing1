package userInterfaceTests.pageObjects;

import elements.Label;
import logger.Logger;
import pages.BasePage;


public class MainPage extends BasePage {

    private static final String START_GAME_LABEL_XPATH = "//a[@class='start__link']";
    private Label startGameLabel = new Label(START_GAME_LABEL_XPATH);

    private Logger logger;

    public MainPage(String siteUrl) {
        super(START_GAME_LABEL_XPATH, siteUrl);
        logger = new Logger(MainPage.class);
    }

    public void clickOnStartingGameLabel() {
        logger.info("Click on start game label");
        startGameLabel.clickElement();
    }
}
