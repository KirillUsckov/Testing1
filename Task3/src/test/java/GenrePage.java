import AutoTest.Elements.ButtonElement;
import AutoTest.Elements.LabelElement;
import AutoTest.Pages.BasePage;

import AutoTest.StringsUtil.CheckStrings;
import AutoTest.StringsUtil.StringsReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Class is used to interact with the genre page
 */
public class GenrePage extends BasePage {

    private final String ELEMENTS_XPATH_FILE_LINK = "src/main/resources/elementsXpath.xml";

    private final String DISCOUNTS_XPATH = "discountsXPath";
    private final String DISCOUNTED_PRICES_XPATH  = "discountedPricesXpath";
    private final String ORIGINAL_PRICES_XPATH = "originalPricesXpath";

    private final CharSequence PERCENT = "%";
    private final char PERCENT_CHAR = '%';

    private final int ONE = 1;
    private final int ZERO = 0;

    private List<WebElement> discountPrices;
    private List<WebElement> originalPrices;

    protected double gameDiscountRate;
    protected double gameInitialPrice;
    protected double gameDiscountedPrice;

    private int indexOfGameWithMaxDiscount;
    private int indexOfGameWithMinDiscount;

    private final Logger logger;

    private StringsReader strReader = new StringsReader(ELEMENTS_XPATH_FILE_LINK);

    private LabelElement result = null;

    public GenrePage() {
        super();
        logger = Logger.getLogger(GenrePage.class);
    }

    public void goToTopSellers() {
        ButtonElement topSellers = new ButtonElement();
        topSellers.findElement("//div[@id='tab_select_TopSellers']//div[@class='tab_content']");///
        topSellers.clickElement();
        logger.info("Click on TopSellers button\nGo to top sellers page");
    }

    public void getDiscountGame(String sorting) {
        logger.info("Searching for discount");

        List<WebElement> gamesWithDiscounts = findPageElements(strReader.getCustomElement(DISCOUNTS_XPATH));

        discountPrices = findPageElements(strReader.getCustomElement(DISCOUNTED_PRICES_XPATH));
        originalPrices = findPageElements(strReader.getCustomElement(ORIGINAL_PRICES_XPATH));

        Assert.assertFalse(gamesWithDiscounts.size() == ZERO);

        LabelElement gameWithDiscount = null;

        if (sorting.contains("max")) {
            gameWithDiscount = getMaxDiscountGame(gamesWithDiscounts);
        } else if (sorting.contains("min")) {
            gameWithDiscount = getMinDiscountGame(gamesWithDiscounts);
        } else {
            logger.warn("Wrong sorting!");
        }

        logger.info("Set game discount from genre page: " + gameDiscountRate);

        if (gameWithDiscount != null) {
            logger.info("Click on game with " + sorting + " discount");
            setPrices();
            gameWithDiscount.clickElement();
        }
    }

    private LabelElement getMaxDiscountGame(List<WebElement> gamesWithDiscounts) {
        logger.info("Searching for the game with max discount");
        int maxDiscount = ZERO;
        String textDiscount;
        for (WebElement gamesDiscount: gamesWithDiscounts) {
            textDiscount = gamesDiscount.getText();
            if (textDiscount.contains(PERCENT)) {
                textDiscount = textDiscount.substring(ONE, textDiscount.indexOf(PERCENT_CHAR));
                gameDiscountRate = Double.parseDouble(textDiscount);

                if (gameDiscountRate > maxDiscount) {
                    indexOfGameWithMaxDiscount = gamesWithDiscounts.indexOf(gamesDiscount);
                    maxDiscount = (int) gameDiscountRate;
                    result = new LabelElement(gamesDiscount);
                }
            } else {
                continue;
            }
        }
        return result;
    }

    private LabelElement getMinDiscountGame(List<WebElement> gamesWithDiscounts) {
        logger.info("Searching for the game with min discount");
        double minDiscount = 100;
        String textDiscount;
        for (WebElement gamesDiscount: gamesWithDiscounts) {
            textDiscount = gamesDiscount.getText();
            if (textDiscount.contains(PERCENT)) {
                textDiscount = textDiscount.substring(ONE, textDiscount.indexOf(PERCENT_CHAR));
                gameDiscountRate = Double.parseDouble(textDiscount);

                if (minDiscount > gameDiscountRate) {
                    minDiscount = gameDiscountRate;
                    indexOfGameWithMinDiscount = gamesWithDiscounts.indexOf(gamesDiscount);
                    result = new LabelElement(gamesDiscount);
                }
            } else {
                continue;
            }
        }
        gameDiscountRate = minDiscount;
        return result;
    }

    private void setPrices() {
        try {
            logger.info("Set games prices.");

            LabelElement initialPrice = new LabelElement(originalPrices.get(indexOfGameWithMaxDiscount));

            String price = CheckStrings.getCorrectPrice(initialPrice.getElementText());
            logger.info("InitialPrice: " + price);
            gameInitialPrice = Double.parseDouble(price.substring(ZERO, price.indexOf(' ')));
            logger.info("Set initial price from genre page: " + gameInitialPrice);

            LabelElement discountedPrice = new LabelElement(discountPrices.get(indexOfGameWithMinDiscount));

            price = CheckStrings.getCorrectPrice(discountedPrice.getElementText());
            logger.info("DiscountedPrice: " + price);
            gameDiscountedPrice = Double.parseDouble(price.substring(ZERO, price.indexOf(' ')));
            logger.info("Set discounted price from genre page: " + gameDiscountedPrice);
        }catch (StringIndexOutOfBoundsException ex) {
            logger.warn(ex);
        }
    }

}
