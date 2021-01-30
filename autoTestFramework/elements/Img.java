package elements;

import org.openqa.selenium.WebElement;

/**
 * The class is responsible for initializing the ImgElement and for
 * implementing the unique element functionality
 */

public class Img extends BaseElement {

    public Img(String locator, String name) {
        super(locator, name);
    }

    public Img(WebElement element, String name) {
        super(element, name);
    }
}
