package autoTestFramework;

import autoTestFramework.browsersUtils.WebDriverInitialization;
import autoTestFramework.constants.CommonConstants;
import autoTestFramework.stringsUtils.StringsReader;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected StringsReader strReader;
    protected String siteUrl;

    @BeforeSuite
    public void setUp() {
        WebDriverInitialization.setBrowser(CommonConstants.SETTINGS_PATH);
        strReader = new StringsReader(CommonConstants.SETTINGS_PATH);
        siteUrl = strReader.getCustomElement(CommonConstants.SITE_URL_TEXT);
    }

    @AfterSuite
    public void finish() {
        WebDriverInitialization.quiteBrowser();
    }
}
