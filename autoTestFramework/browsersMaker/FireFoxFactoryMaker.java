package browsersMaker;

import browsersFactory.BrowserFactory;
import browsersFactory.FireFoxFactory;

/**
 * The class is responsible for initializing the FireFoxFactory
 */

public class FireFoxFactoryMaker implements BrowserMaker {

    @Override
    public BrowserFactory createBrowser() {
        return new FireFoxFactory();
    }
}
