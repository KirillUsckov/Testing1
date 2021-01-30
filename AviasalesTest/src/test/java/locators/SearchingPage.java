package locators;

public class SearchingPage {

    public static final String FINISHED_LOADER_XPATH = "//div[@class='loader__stripes --animation-started --blue']";

    public static final String SUBSCIPTION_POPUP_XPATH = "//div[@class='subscriptions-popup']";

    public static final String BUY_BUTTON_XPATH = "//div[@class='buy-button']//a[@target='_blank']";
    public static final String TICKET_PRICE_LABEL_XPATH = "//div[@class='buy-button']//span[@data-testid='price-with-logic']";

    public static final String DIRECT_FLIGHT_BUTTON_XPATH = "//li[@class='sorting__tab']//span[@class='sorting__title-wrap']" +
                                                                                                        "[contains(text(), 'Прямой')]";

    public static final String BAGGAGE_FILTER_BUTTON_XPATH = "//div[@class='filters__item filter --baggage']//div";

    public static final String WITHOUT_BAGGAGE_CHECKBOX_XPATH = "//div[@class='filters__item filter --baggage is-opened']" +
                                                                    "//div[@class='checkboxes-list__list --overflow-hidden']//div[2]";

    public static final String BAG_IMG_XPATH = "//span[@class='bag-icon --baggage']";

    private SearchingPage() {}
}