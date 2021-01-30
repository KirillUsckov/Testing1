package autoTestFramework.browsersUtils;

import autoTestFramework.stringsUtils.XMLStringsReader;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;

/**
 * The class is responsible for set browser options
 */

public class BrowserProperties {

    private BrowserProperties() {}

    public static ChromeOptions getChromeSettings() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", getPrefs());
        return options;
    }

    public static FirefoxOptions getFireFoxSettings() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("download.prompt_for_download", true);
        options.addPreference("download.directory_upgrade", true);
        //options.addPreference("intl.accept_languages", getLocalisation());
        return options;
    }

    private static HashMap<String, Object> getPrefs() {
        HashMap<String, Object> browserPrefs = new HashMap<>();
        browserPrefs.put("download.prompt_for_download", true);
        browserPrefs.put("download.directory_upgrade", true);
        //browserPrefs.put("intl.accept_languages", getLocalisation());
        return browserPrefs;
    }

    public static String getLocalisation() {
        XMLStringsReader strReader = new XMLStringsReader("src/main/resources/configuration.xml");
        return strReader.getCustomElement("localisation");
    }
}
