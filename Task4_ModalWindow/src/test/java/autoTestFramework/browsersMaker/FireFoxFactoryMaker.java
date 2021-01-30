package autoTestFramework.browsersMaker;

import autoTestFramework.browsersFactory.BrowserFactory;
import autoTestFramework.browsersFactory.FireFoxFactory;

/**
 * The class is responsible for initializing the FireFoxFactory
 */

public class FireFoxFactoryMaker implements BrowserMaker {

    @Override
    public BrowserFactory createBrowser() {
        return new FireFoxFactory();
    }
}
