package locators;

public class MainPage {

    public static final String MAIN_PAGE_LOCATOR = "//span[@class='form-submit__plane']";

    public static final String CITY_DEPARTURE_TEXTBOX_XPATH = "//input[@id='origin']";
    public static final String CITY_DESTINATION_TEXTBOX_XPATH = "//input[@aria-labelledby='destination-label']";

    public static final String DEPARTURE_DATE_TEXTBOX_XPATH = "//div[@class='trip-duration__input-wrapper --departure']";
    public static final String DATE_CELL_BUTTON_XPATH = "//div[@class='calendar__day-cell ']";
    public static final String RETURN_DATE_TEXTBOX_XPATH = "//div[@class='trip-duration__input-wrapper --return']";

    public static final String WITHOUT_TICKET_BUTTON_XPATH = "//div[@class='trip-duration__content-head']//button";
    public static final String SEARCH_TICKET_BUTTON_XPATH = "//div[@class='avia-form__submit']//button";

    public static final String BOOKING_CHECKBOX_XPATH = "//label[@class='of_input_checkbox__label']";

    private MainPage() { }
}
