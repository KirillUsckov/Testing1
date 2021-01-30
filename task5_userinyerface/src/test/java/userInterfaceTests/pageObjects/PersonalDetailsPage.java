package userInterfaceTests.pageObjects;

import elements.Label;
import logger.Logger;
import pages.BasePage;

public class PersonalDetailsPage extends BasePage {

    private static final String PAGE_INDICATOR_LABEL = "//div[@class='page-indicator']";
    private Label numberOfPageLabel = new Label(PAGE_INDICATOR_LABEL);
    private Logger logger;

    public PersonalDetailsPage() {
        super(PAGE_INDICATOR_LABEL);
        logger = new Logger(PersonalDetailsPage.class);
    }

    public boolean isPersonalDetailsPageOpen(String pagePosition) {
        numberOfPageLabel.findElement();
        return numberOfPageLabel.getElementText().matches(pagePosition);
    }
}
