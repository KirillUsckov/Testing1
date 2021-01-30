package PageObjects;

import AutoTest.Elements.ButtonElement;
import AutoTest.Elements.CheckBoxElement;
import AutoTest.Elements.TextBoxElement;
import AutoTest.Pages.BasePage;

import locators.MainPage;

import projectUtils.RandomDate;

import org.apache.log4j.Logger;


public class AviasalesMainPage extends BasePage {
    private Logger logger;

    public AviasalesMainPage(String locator, String siteUrl) {
        super(locator, siteUrl);
        logger = Logger.getLogger(AviasalesMainPage.class);
    }

    public void enterCityDeparture(String city) {
        logger.info("Searching for CITY_DEPARTURE_TEXTBOX");
        TextBoxElement departureCity = new TextBoxElement(MainPage.CITY_DEPARTURE_TEXTBOX_XPATH);
        logger.info("Set departure city: " + city);
        departureCity.setText(city);
    }

    public void enterDestinationCity(String city) {
        logger.info("Searching for CITY_DESTINATION_TEXTBOX");
        TextBoxElement destinationCity = new TextBoxElement(MainPage.CITY_DESTINATION_TEXTBOX_XPATH);
        logger.info("Set destination city: " + city);
        destinationCity.setText(city);
    }

    public void enterDepartureDate() {
        logger.info("Searching for DEPARTURE_DATE_TEXTBOX");
        TextBoxElement departureDate = new TextBoxElement(MainPage.DEPARTURE_DATE_TEXTBOX_XPATH);
        logger.info("Date click");
        departureDate.clickElement();

        logger.info("Set departure date");
        ButtonElement dateCellButton = new ButtonElement(RandomDate.getRandomDate());
        dateCellButton.clickElement();
    }

    public void withoutReturnDate() {
        logger.info("Searching for RETURN_DATE_TEXTBOX");
        TextBoxElement returnDate = new TextBoxElement(MainPage.RETURN_DATE_TEXTBOX_XPATH);
        returnDate.clickElement();

        logger.info("Searching for WITHOUT_TICKET_BUTTON");
        ButtonElement withoutTicket = new ButtonElement(MainPage.WITHOUT_TICKET_BUTTON_XPATH);
        logger.info("Click on button");
        withoutTicket.clickElement();
    }

    public void withoutHotel() {
        logger.info("Searching for BOOKING_CHECKBOX");
        CheckBoxElement checkBox = new CheckBoxElement(MainPage.BOOKING_CHECKBOX_XPATH);
        logger.info("Click on checkbox");
        checkBox.clickElement();
    }

    public void clickSearchingButton() {
        logger.info("Searching for SEARCH_TICKET_BUTTON");
        ButtonElement searchButton = new ButtonElement(MainPage.SEARCH_TICKET_BUTTON_XPATH);
        logger.info("Click on button");
        searchButton.clickElement();
    }
}
