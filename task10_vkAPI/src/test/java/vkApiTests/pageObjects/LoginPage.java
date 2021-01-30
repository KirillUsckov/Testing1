package vkApiTests.pageObjects;

import elements.Button;
import elements.TextBox;
import logger.Logger;
import pages.BasePage;

public class LoginPage extends BasePage {
    private static final Logger LOG = new Logger(LoginPage.class);

    private static final String LOGIN_DATA_XPATH = "//div[@id='index_login']";
    private static final String EMAIL_TEXTBOX_XPATH = LOGIN_DATA_XPATH + "//input[@name='email']";
    private static final String PASSWORD_TEXTBOX_XPATH = LOGIN_DATA_XPATH + "//input[@name='pass']";
    private static final String LOGIN_BUTTON_XPATH = "//button[@id='index_login_button']";

    public LoginPage(String url) {
        super(LOGIN_DATA_XPATH, url);
    }

    public void enterEmail(String email) {
        LOG.info("Инициализация поля ввода почты");
        TextBox emailTextBox = new TextBox(EMAIL_TEXTBOX_XPATH);
        LOG.info("Отправление данных в поле");
        emailTextBox.setElementText(email);
    }

    public void enterPassword(String password) {
        LOG.info("Инициализация поля ввода пароля");
        TextBox passwdTextBox = new TextBox(PASSWORD_TEXTBOX_XPATH);
        LOG.info("Отправление данных в поле");
        passwdTextBox.setElementText(password);
    }

    public void clickLoginButton() {
        LOG.info("Инициализация кнопки входа");
        Button loginButton = new Button(LOGIN_BUTTON_XPATH);
        LOG.info("Нажатие на кнопку");
        loginButton.clickElement();
    }

}
