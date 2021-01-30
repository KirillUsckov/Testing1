package projectUtils;

import AutoTest.ElementsUtils.ElementsList;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class MinAndMaxElementSearcher {
    private Logger logger;
    private ElementsList elementsList;
    private int value;
    private int index;

    public MinAndMaxElementSearcher(ElementsList list) {
        elementsList = list;
        logger = Logger.getLogger(MinAndMaxElementSearcher.class);
    }

    public WebElement getElementWithMinValue() {
        elementsList.setElementsArray(" ", "");
        elementsList.setMinValue();
        index = elementsList.getIndex();
        value = elementsList.getValue();
        return elementsList.getElement(index);

    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }

    public boolean isElementFirst() {
        if(index == 0) {
            logger.info("The ticket was the first!");
        } else {
            logger.warn("The ticket wasn't the first!");
        }
        return index == 0;
    }

    public boolean isElementsLast() {
        if(index == elementsList.getElementsListSize() - 1) {
             logger.info("The ticket was last!");
        } else {
            logger.warn("The ticket wasn't the last!");
        }
        return index == elementsList.getElementsListSize() - 1;
    }

}
