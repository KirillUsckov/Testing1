import AutoTest.Elements.ButtonElement;
import AutoTest.Elements.SelectElement;
import AutoTest.Pages.BasePage;
import AutoTest.StringsUtil.StringsReader;
import org.apache.log4j.Logger;

/**
 * Class is used to proof of age to view the game page
 */

public class ProofOfAgePage extends BasePage {
    private final String ELEMENTS_XPATH_FILE_LINK = "src/main/resources/elementsXpath.xml";
    private StringsReader strReader = new StringsReader(ELEMENTS_XPATH_FILE_LINK);
    private final String SELECT_YEAR_XPATH = "selectYearXpath";
    private final String YEAR_FOR_VERIFICATION = "yearForVerification";
    private final String VIEW_PAGE_BUTTON_XPATH = "viewPageButtonXpath";
    private final Logger logger;

    ProofOfAgePage() {
        super();
        logger = Logger.getLogger(ProofOfAgePage.class);
    }

    public void selectBirthdayYear() {
        logger.info("Searching for select year selector");
        SelectElement selectYear = new SelectElement();
        selectYear.findElement(strReader.getCustomElement(SELECT_YEAR_XPATH));
        logger.info("Enter an year");
        selectYear.selectOption(strReader.getCustomElement(YEAR_FOR_VERIFICATION));
    }

    public void viewPage() {
        logger.info("Searching for viewGamePage button");
        ButtonElement viewGamePage = new ButtonElement();
        viewGamePage.findElement(strReader.getCustomElement(VIEW_PAGE_BUTTON_XPATH));
        logger.info("Click on button for view game page");
        viewGamePage.clickElement();
    }
}
