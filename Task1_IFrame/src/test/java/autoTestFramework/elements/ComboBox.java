package autoTestFramework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * The class is responsible for initializing the SelectElement and for
 * implementing the unique element functionality
 */

public class ComboBox extends BaseElement {

    private final String OPTION_TAG_NAME = "option";

    public ComboBox(String locator) {
        super(locator);
    }

    public void selectOption(String optionName) {
        findElement();
        List<WebElement> options = element.findElements(By.tagName(OPTION_TAG_NAME));

        for (WebElement option : options) {
            if(option.getText().trim().equals(optionName)) {
                option.click();
            }
        }
    }

}
