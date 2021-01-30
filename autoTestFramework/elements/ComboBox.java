package elements;

import logger.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * The class is responsible for initializing the SelectElement and for
 * implementing the unique element functionality
 */

public class ComboBox extends BaseElement {
    private static final Logger LOG = new Logger(ComboBox.class);

    private final String OPTION_TAG_NAME = "option";

    public ComboBox(String locator, String name) {
        super(locator, name);
    }

    public void selectOption(String optionName) {
        try {
            findElement();
            List<WebElement> options = element.findElements(By.tagName(OPTION_TAG_NAME));

            for (WebElement option : options) {
                if (option.getText().trim().equals(optionName)) {
                    option.click();
                }
            }
        } catch (Exception e) {
            LOG.error("ELEMENT " + name + ":" + e);
        }
    }

}
