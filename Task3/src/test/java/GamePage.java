import AutoTest.Elements.LabelElement;
import AutoTest.Pages.BasePage;

import AutoTest.StringsUtil.CheckStrings;
import AutoTest.StringsUtil.StringsReader;
import org.apache.log4j.Logger;

/**
 * Class is used to interact with page of selected game
 */
public class GamePage extends BasePage {
    private final String ELEMENTS_XPATH_FILE_LINK = "src/main/resources/elementsXpath.xml";

    private StringsReader strReader = new StringsReader(ELEMENTS_XPATH_FILE_LINK);

    private final String DISCOUNT_XPATH = "discountXpath";
    private final String INITIAL_PRICE_XPATH= "initialPriceXpath";
    private final String DISCOUNTED_PRICE_XPATH = "discountedPriceXpath";

    protected double initialPriceOnPage;
    protected double discountedPriceOnPage;
    protected double discountOnPage;
    private final Logger logger;

    GamePage() {
        super();
        logger = Logger.getLogger(GamePage.class);
    }

    public void setPrices() {
        try {
            logger.info("Searching for discount label");
            LabelElement discount = new LabelElement();
            discount.findElement(strReader.getCustomElement(DISCOUNT_XPATH));
            discountOnPage = Double.parseDouble(discount.getElementText()
                    .substring(1, discount.getElementText().indexOf('%')));
            logger.info("Set discount: " + discountOnPage);

            logger.info("Searching for initial price label");
            LabelElement initialPrice = new LabelElement();
            initialPrice.findElement(strReader.getCustomElement(INITIAL_PRICE_XPATH));
            String price = CheckStrings.getCorrectPrice(initialPrice.getElementText());
            initialPriceOnPage = Double.parseDouble(price.substring(0, price.indexOf(' ')));
            logger.info("Set initial price: " + initialPriceOnPage);

            logger.info("Searching for discounted price label");
            LabelElement discountedPrice = new LabelElement();
            discountedPrice.findElement(strReader.getCustomElement(DISCOUNTED_PRICE_XPATH));
            price = CheckStrings.getCorrectPrice(discountedPrice.getElementText());
            discountedPriceOnPage = Double.parseDouble(price.substring(0, price.indexOf(' ')));
            logger.info("Set discounted price: " + discountedPriceOnPage);

        } catch (Exception ex) {
            logger.warn(ex);
        }

    }

    public boolean checkPrices(double gameDiscountRate, double gameDiscountedPrice, double gameInitialPrice) {
        logger.info("Check prices.");
        boolean result = CheckStrings.checkValue(gameDiscountRate, discountOnPage) &&
                CheckStrings.checkValue(gameDiscountedPrice, discountedPriceOnPage) &&
                CheckStrings.checkValue(gameInitialPrice, initialPriceOnPage);
        if (result) {
            logger.info("Result of checking prices: " + result);
        } else {
            logger.warn("Result of checking prices: " + result);
        }
        return result;
    }
}
