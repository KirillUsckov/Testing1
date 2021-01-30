package vkApiTests.pageObjects;

import logger.Logger;
import vkApiTests.contsants.MenuLocators;
import elements.Button;
import pages.BasePage;

public class FeedPage extends BasePage {
    private static final Logger LOG = new Logger(FeedPage.class);

    public static final String FEED_ROWS_XPATH = "//div[@class='feed_row ']";

    public FeedPage() {
        super(FEED_ROWS_XPATH);
    }

    public void clickProfileMenuButton() {
        LOG.info("Инициализация кнопки меню для перехода в профиль");
        Button profileButton = new Button(MenuLocators.PROFILE_XPATH);
        LOG.info("Нажатие на кнопку");
        profileButton.clickElement();
    }
}
