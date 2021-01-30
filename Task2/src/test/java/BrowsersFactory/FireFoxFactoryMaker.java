package BrowsersFactory;

public class FireFoxFactoryMaker implements BrowserMaker{
    @Override
    public BrowserFactory createBrowser(int codeOS) {
        return new FireFoxFactory(codeOS);
    }
}
