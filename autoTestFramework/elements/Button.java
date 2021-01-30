package elements;

import org.openqa.selenium.WebElement;

/**
 * The class is responsible for initializing the ButtonElement and for
 * implementing the unique element functionality
 */

public class Button extends BaseElement {

    public Button(String locator, String name) {
        super(locator, name);
    }

    public Button(WebElement element, String name) {
        super(element, name);
    }
}
