package autoTestFramework;

import autoTestFramework.browsersUtils.WebDriverInitialization;
import autoTestFramework.stringsUtils.XMLStringsReader;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected XMLStringsReader strReader;
    protected String siteUrl;

    @BeforeSuite
    public void setUp() {
        WebDriverInitialization.setBrowser(CommonConstants.SETTINGS_PATH);
        strReader = new XMLStringsReader(CommonConstants.SETTINGS_PATH);
        siteUrl = strReader.getCustomElement(CommonConstants.SITE_URL_TEXT);
    }

    @AfterSuite
    public void finish() {
        WebDriverInitialization.quiteBrowser();

    }
}
