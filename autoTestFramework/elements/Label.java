package elements;

import org.openqa.selenium.WebElement;

/**
 * The class is responsible for initializing the LabelElement and for
 * implementing the unique element functionality
 */

public class Label extends BaseElement {

    public Label(WebElement webElement, String name) {
        super(webElement, name);
    }

    public Label(String locator, String name) {
        super(locator, name);
    }


}
