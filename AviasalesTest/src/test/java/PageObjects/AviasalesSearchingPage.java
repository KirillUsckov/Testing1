package PageObjects;

import AutoTest.Elements.ButtonElement;
import AutoTest.Elements.CheckBoxElement;
import AutoTest.ElementsUtils.ElementsList;
import AutoTest.Pages.BasePage;

import locators.SearchingPage;

import projectUtils.MinAndMaxElementSearcher;

import org.apache.log4j.Logger;

public class AviasalesSearchingPage extends BasePage {

    private Logger logger;
    private MinAndMaxElementSearcher searcher;

    public AviasalesSearchingPage(String locator) {
        super(locator);
        logger = Logger.getLogger(AviasalesSearchingPage.class);
    }

    public void findTickedWithLowestPrice() {
        ElementsList elementsList = new ElementsList(SearchingPage.TICKET_PRICE_LABEL_XPATH);
        searcher = new MinAndMaxElementSearcher(elementsList);
        searcher.getElementWithMinValue();
        logger.info("Min price: " + searcher.getValue());
    }

    public void clickDirectButton() {
        logger.info("Searching for direct flight button");
        try {
            ButtonElement directButton = new ButtonElement(SearchingPage.DIRECT_FLIGHT_BUTTON_XPATH);
            logger.info("Click on button");
            directButton.clickElement();
        } catch (NullPointerException ex) {
            logger.warn(ex);
        }
    }

    public boolean checkElementPosition(String searchingPosition) {
        switch (searchingPosition) {
            case "top":
                return searcher.isElementFirst();
            case "bottom":
                return searcher.isElementsLast();
            default:
                return true;
        }
    }

    public void clickBaggageFilterButton() {
        logger.info("Searching for BAGGAGE_FILTER_BUTTON");
        try {
            ButtonElement baggageFilterButton = new ButtonElement(SearchingPage.BAGGAGE_FILTER_BUTTON_XPATH);
            logger.info("Click on BAGGAGE_FILTER_BUTTON");
            baggageFilterButton.clickElement();
        } catch (Exception ex) {
            logger.warn(ex);
        }
    }

    public void setCheckboxState() {
        logger.info("Searching for WITHOUT_BAGGAGE_CHECKBOX");
        CheckBoxElement el = new CheckBoxElement(SearchingPage.WITHOUT_BAGGAGE_CHECKBOX_XPATH);
        logger.info("Click on WITHOUT_BAGGAGE_CHECKBOX");
        el.clickElement();
    }

    public boolean checkFiltredTickets() {
        ElementsList list = new ElementsList(SearchingPage.BUY_BUTTON_XPATH);
        ElementsList list1 = new ElementsList(SearchingPage.BAG_IMG_XPATH);
        if (list.getElementsListSize() == list1.getElementsListSize()) {
            logger.info("All tickets have baggage");
        } else {
            logger.warn("Not all tickets have baggage");
        }
        return list.getElementsListSize() == list1.getElementsListSize();
    }




}
