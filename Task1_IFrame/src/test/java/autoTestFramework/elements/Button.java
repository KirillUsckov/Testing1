package autoTestFramework.elements;

import org.openqa.selenium.WebElement;

/**
 * The class is responsible for initializing the ButtonElement and for
 * implementing the unique element functionality
 */

public class Button extends BaseElement {

    public Button(String locator) {
        super(locator);
    }

    public Button(WebElement element) {
        super(element);
    }

    public String getHref() {
        findElement();
        return element.getAttribute("href");
    }
}
