package autoTestFramework.elements;

import org.openqa.selenium.WebElement;

/**
 * The class is responsible for initializing the CheckBoxElement and for
 * implementing the unique element functionality
 */

public class CheckBox extends BaseElement {

    public CheckBox(String selector) {
        super(selector);
    }

    public CheckBox(WebElement element) {
        super(element);
    }
}
