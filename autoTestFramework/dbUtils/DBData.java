package dbUtils;

import constants.CommonConstants;
import stringsUtils.XMLStringsReader;

public class DBData {
    private static String serverUrl;
    private static String username;
    private static String password;

    private DBData(String login, String passwd) {
        XMLStringsReader reader = new XMLStringsReader(CommonConstants.SETTINGS_PATH);
        serverUrl = reader.getCustomElement("dbServer");
        username = login;
        password = passwd;
    }


    public static DBData setDataToConnect(String login, String passwd) {
        return new DBData(login, passwd);
    }

    public DBConnector getConnector () {
        return DBConnector.create(serverUrl, username, password);
    }
}
