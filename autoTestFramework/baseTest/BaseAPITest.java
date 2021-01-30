package baseTest;

import constants.CommonConstants;
import org.testng.annotations.BeforeSuite;
import stringsUtils.XMLStringsReader;

public class BaseAPITest {
    protected String siteUrl;

    @BeforeSuite
    public void setUp() {
        XMLStringsReader reader = new XMLStringsReader(CommonConstants.SETTINGS_PATH);
        siteUrl = reader.getCustomElement(CommonConstants.SITE_URL_TEXT);
    }
}
