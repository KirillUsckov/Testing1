import models.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Kirill Uskov
 * @version 1.0
 */

public class BasicAuthorisationTestCases {

    private User user;


    @DataProvider (name = "loginTest")
    public Object[][] setTestDataForLogin() {
        return new Object[][] {{TestData.USER_NAME_USER, TestData.USER_PASSWORD_FOR_USER}};
    }

    /**
     * Create User.
     */

    @Test (dataProvider = "loginTest")
    public void login(String userName, String userPassword) {
        user = new User(userName, userPassword);
        user.loginUser();
    }
}
