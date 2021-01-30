package autoTestFramework.browsersMaker;

import autoTestFramework.browsersFactory.BrowserFactory;

/**
 * The interface is responsible for defining the functional of classes
 * that implement the interface
 */

public interface BrowserMaker {
    BrowserFactory createBrowser();
}
