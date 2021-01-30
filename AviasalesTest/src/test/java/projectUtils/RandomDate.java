package projectUtils;

import AutoTest.ElementsUtils.ElementsList;
import org.openqa.selenium.WebElement;

import java.util.Random;

/**
 * RandomDate class is used for get random date
 */

public class RandomDate {

    public static final String DATE_CELL_BUTTON_XPATH = "//div[@class='calendar__day-cell ']";

    public static WebElement getRandomDate() {
        ElementsList list = new ElementsList(DATE_CELL_BUTTON_XPATH);
        Random randomDate = new Random();
        int dateId = randomDate.nextInt(list.getElementsListSize());

        return list.getElement(dateId);
    }
}
