package BrowsersFactory;

public class ChromeFactoryMaker implements BrowserMaker {
    @Override
    public BrowserFactory createBrowser(int codeOS) {
        return new ChromeFactory(codeOS);
    }
}
