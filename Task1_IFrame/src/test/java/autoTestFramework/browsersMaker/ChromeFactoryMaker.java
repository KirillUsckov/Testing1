package autoTestFramework.browsersMaker;

import autoTestFramework.browsersFactory.BrowserFactory;
import autoTestFramework.browsersFactory.ChromeFactory;

/**
 * The class is responsible for initializing the ChromeFactory
 */

public class ChromeFactoryMaker implements BrowserMaker {

    @Override
    public BrowserFactory createBrowser() {
        return new ChromeFactory();
    }

}
