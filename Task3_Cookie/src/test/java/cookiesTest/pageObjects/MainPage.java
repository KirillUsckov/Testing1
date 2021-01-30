package cookiesTest.pageObjects;

import autoTestFramework.pages.BasePage;
import org.apache.log4j.Logger;

public class MainPage extends BasePage {

    private static final String MAIN_PAGE_LOCATOR = "//h1[contains(text(), 'Example Domain')]";

    private Logger logger;


    public MainPage(String siteUrl) {
        super(MAIN_PAGE_LOCATOR, siteUrl);
        logger = Logger.getLogger(MainPage.class);
        logger.info("Initialisation of webDriverExtensions and logger");
    }


}
