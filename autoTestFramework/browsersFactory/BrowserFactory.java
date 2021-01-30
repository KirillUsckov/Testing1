package browsersFactory;

import org.openqa.selenium.WebDriver;

/**
 * The interface is responsible for defining the functional of classes
 * that implement the interface
 */

public interface BrowserFactory {
   WebDriver getBrowserDriver();
}


