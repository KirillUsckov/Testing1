package autoTestFramework.elementsUtils;

import autoTestFramework.browsersUtils.WebDriverInitialization;
import autoTestFramework.stringsUtils.CheckStrings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * The class is responsible for initializing and for interaction with the ElementsList
 */

public class ElementsList {
    private static List<WebElement> elementsList;
    private static WebDriver webDriver;

    private int value;
    private int index;
    private int[] elementsArray;

    public ElementsList(String xpath) {
        webDriver = WebDriverInitialization.getInstance();
        setElementsList(xpath);
    }

    public void setElementsList(String xpath) {
        elementsList = webDriver.findElements(By.xpath(xpath));
    }

    public int getElementsListSize() {
        return elementsList.size();
    }

    public WebElement getElement(int id) {
        return elementsList.get(id);
    }

    public List<WebElement> getElementsList() {
        return elementsList;
    }

    public void setElementsArray(String oldString, String newString) {
        String text;
        elementsArray = new int[getElementsListSize()];
        for (int i = 0; i < getElementsListSize(); i++) {
            text = CheckStrings.getIntFromString(getElement(i).getText(), oldString, newString);
            elementsArray[i] = Integer.parseInt(text);
        }
    }

    public void setMinValue() {
        for (int i = 0; i < elementsArray.length; i++) {
            if (i == 0 || elementsArray[i] < value) {
                index = i;
                value = elementsArray[i];
            }
        }
    }

    public int getElementsArrayLenght() {
        return elementsArray.length;
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return  value;
    }

}
