package stringsUtils;

import constants.CommonConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private static FileInputStream fis;
    private static Properties property = new Properties();

    public static String getPropertyFromProperties(String propertyName) {
        try {
            fis = new FileInputStream(CommonConstants.LOGGER_PROPERTIES_PATH);
            property.load(fis);

            return property.getProperty(propertyName);
        } catch (IOException e) {
        }
        return null;
    }
}
