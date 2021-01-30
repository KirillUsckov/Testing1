package elements;

import org.openqa.selenium.WebElement;

/**
 * The class is responsible for initializing the CheckBoxElement and for
 * implementing the unique element functionality
 */

public class CheckBox extends BaseElement {

    public CheckBox(String selector, String name) {
        super(selector, name);
    }

    public CheckBox(WebElement element, String name) {
        super(element, name);
    }
}
