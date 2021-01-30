package models;

import Utils.XMLFileReader;
import com.jayway.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * User class is used for initialization and interaction with user.
 */
public class User {

    private static final String CONFIGURATION_FILE = "src/test/resources/configuration.xml";
    private static final String SITE_URL = "siteUrl";

    private static final String EMPTY_USER_NAME_MESSAGE = "EMPTY USER NAME";
    private static final String EMPTY_USER_PASSWORD_MESSAGE = "EMPTY USER PASSWORD";

    private String userName, userPassword, siteUrl;

    private Logger logger;


    /**
     *
     * @param userName
     * @param userPassword
     */
    public User(String userName, String userPassword) {
        checkUserData(userName, userPassword);
        logger = Logger.getLogger(User.class);

        logger.info("Write data:");
        logger.info("Users name was written");
        this.userName = userName;
        logger.info("Users password was written");
        this.userPassword = userPassword;

        setSiteUrl();
    }

    private void checkUserData(String userName, String userPassword) {
        Assert.assertFalse(userName.length() == 0, EMPTY_USER_NAME_MESSAGE);
        Assert.assertFalse(userPassword.length() == 0, EMPTY_USER_PASSWORD_MESSAGE);
    }

    private void setSiteUrl() {
        XMLFileReader reader = new XMLFileReader(CONFIGURATION_FILE);
        logger.info("Site url was written");
        siteUrl = reader.getCustomElement(SITE_URL);
    }

    /**
     * Login.
     */
    public void loginUser() {
        Response response = given().auth()
                .basic(userName, userPassword)
                .when()
                .get(siteUrl)
                .then()
                .assertThat()
                .body("authenticated", equalTo(true))
                .body("user", equalTo(userName))
                .extract()
                .response();
        logger.info(response.getBody().asString());
    }

}
