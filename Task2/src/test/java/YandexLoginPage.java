import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class YandexLoginPage {

    private final int TWENTY_SECONDS = 20;

    private static YandexLoginPage yandexLoginPage;

    private WebDriver driver;

    private String inputUserName;
    private String inputPasswordId;
    private String buttonLogin;
    private String inboxClassName;
    private String profileNameXpath;

    YandexLoginPage(Properties properties, WebDriver driver) {
        inputUserName = properties.getProperty("loginName");
        inputPasswordId = properties.getProperty("passwordId");
        buttonLogin = properties.getProperty("loginButton");
        inboxClassName = properties.getProperty("inboxClassName");
        profileNameXpath = properties.getProperty("profileNameXpath");
        this.driver = driver;
    }

    public static YandexLoginPage getInstance(Properties properties, WebDriver driver) {
        if (yandexLoginPage == null){
            yandexLoginPage = new YandexLoginPage(properties, driver);
        }
        return yandexLoginPage;
    }

    void enterUserName(String userName) {
        driver.manage().timeouts().implicitlyWait(TWENTY_SECONDS, TimeUnit.SECONDS);
        driver.findElement(By.name(inputUserName)).sendKeys(userName);
    }

    void clickLoginButton() {
        driver.findElement(By.xpath(buttonLogin)).click();
    }

    void enterPassword(String password) {
        WebElement passwordInput = new WebDriverWait(driver, TWENTY_SECONDS)
                .until(driver -> driver.findElement(By.id(inputPasswordId)));
        passwordInput.sendKeys(password);
    }

    /**
     * Проверка успешной авторизации осуществляется путем проверки соответствия
     * логина пользователя и части email пользователя до "@"
     * @param yandexMainPage
     * @param userEmail
     * @return
     */
    Boolean checkLogin(YandexMainPage yandexMainPage, String userEmail) {
        WebElement inbox = new WebDriverWait(driver, TWENTY_SECONDS)
                .until(driver -> driver.findElement(By.className(inboxClassName)));
        yandexMainPage.openYandex();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement profileName = driver.findElement(By.xpath(profileNameXpath));
        userEmail = userEmail.substring(1, userEmail.indexOf('@'));

        return profileName.getText().contains(userEmail);
    }
}
