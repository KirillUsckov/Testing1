package elementsUtils;

import browsersUtils.WebDriverInitialization;
import com.sun.xml.bind.v2.model.core.EnumLeafInfo;
import logger.Logger;
import stringsUtils.CheckStrings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The class is responsible for initializing and for interaction with the ElementsList
 */

public class ElementsList {
    private final static Logger LOG = new Logger(EnumLeafInfo.class);
    private static List<WebElement> elementsList;
    private static WebDriver webDriver;

    private int value;
    private int index;
    private int[] elementsArray;
    private String xpath;
    private String name;

    public ElementsList(String xpath, String name) {
        webDriver = WebDriverInitialization.getInstance();
        this.xpath = xpath;
        this.name = name;
    }

    public void findElements() {
        elementsList = webDriver.findElements(By.xpath(xpath));
    }

    public int getSize() {
        return elementsList.size();
    }

    public WebElement getElement(int id) {
        return elementsList.get(id);
    }

    public List<WebElement> getElementsList() {
        return elementsList;
    }

    public List<String> getListOfElementsTexts() {
        try {
            ArrayList<String> elementsTexts = new ArrayList<>();
            for (WebElement el : elementsList) {
                elementsTexts.add(el.getText());
            }
            return elementsTexts;
        } catch (Exception e) {
            LOG.error("ElementList " + name + ": " + e);
            return null;
        }
    }

    public void setElementsArray(String oldString, String newString) {
        try {
            String text;
            elementsArray = new int[getSize()];
            for (int i = 0; i < getSize(); i++) {
                text = CheckStrings.getIntFromString(getElement(i).getText(), oldString, newString);
                elementsArray[i] = Integer.parseInt(text);
            }
        } catch (Exception e) {
            LOG.error("ElementList " + name + ": " + e);
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

    public List<Integer> getRandomElementsIndexes(int elementsAmount, int maxIndex) {
        try {
            List<Integer> elementsIndexes = new ArrayList<>();
            Random r = new Random();
            do {
                for (int i = elementsIndexes.size(); i < elementsAmount; i++) {
                    elementsIndexes.add(r.nextInt(maxIndex));
                    excludeDuplicates(elementsIndexes);
                }
            } while (elementsIndexes.size() < elementsAmount);

            return elementsIndexes;
        } catch (Exception e) {
            LOG.error("ElementList " + name + ": " + e);
            return null;
        }
    }

    private List<Integer> excludeDuplicates(List<Integer> indexList) {
        try {
            Collections.sort(indexList);
            if (indexList.size() > 1) {
                for (int i = 0; i < indexList.size() - 1; i++) {
                    if (indexList.get(i) == indexList.get(i + 1)) {
                        indexList.remove(i);
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("ElementList " + name + ": " + e);
        }

        return indexList;
    }

}
