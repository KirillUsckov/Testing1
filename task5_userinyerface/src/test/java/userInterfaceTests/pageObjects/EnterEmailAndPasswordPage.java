package userInterfaceTests.pageObjects;

import elements.*;
import elementsUtils.ElementsList;
import logger.Logger;
import pages.BasePage;
import userInterfaceTests.models.RandomUser;

public class EnterEmailAndPasswordPage extends BasePage {
    private static final String ENTER_PASSWORD_TEXTBOX_XPATH = "//div[@class='login-form__field-row']//input";
    private static final String ACCEPT_COOKIES_BUTTON = "//button[@class='button button--solid button--transparent']";
    private static final String DROPDOWN_ELEMENT_XPATH = "//div[@class='dropdown__list-item']";
    private static final String TIMER_LABEL_XPATH = "//div[@class='timer timer--white timer--center']";

    private Button hideHelpFormButton = new Button("//span[contains(text(),'Send')]");
    private Button acceptingCookiesButton = new Button(ACCEPT_COOKIES_BUTTON);
    private Button nextButton = new Button("//a[@class='button--secondary']");

    private TextBox passwordInput = new TextBox("//input[@placeholder='Choose Password']");
    private TextBox emailInput = new TextBox("//input[@placeholder='Your email']");
    private TextBox domainInput = new TextBox("//input[@placeholder='Domain']");

    private Label howCanWeHelpLabel = new Label("//h2[@class='help-form__title']");

    private Img dropDownImg = new Img("//span[@class='icon icon-chevron-down']");

    private CheckBox acceptingTermsCheckBox = new CheckBox("//span[@class='checkbox__box']");



    private RandomUser user;
    private Logger logger;

    public EnterEmailAndPasswordPage() {
        super(ENTER_PASSWORD_TEXTBOX_XPATH);
        user = new RandomUser();
        logger = new Logger(EnterEmailAndPasswordPage.class);
    }

    public void enterRandomPasswordAndEmail() {
        setPassword(user.getRandomPassword());
        setEmail(user.getEmail());
        setDomain(user.getDomain());
        setRandomDomainEnd();
    }

    public void setPassword(String password) {
        logger.info("Delete old text and set new text in password textBox");
        passwordInput.clearAndSetText(password);
    }

    public void setEmail(String email) {
        logger.info("Delete old text and set new text in email textBox");
        emailInput.clearAndSetText(email);
    }

    public void setDomain(String domain) {
        logger.info("Delete old text and set new text in domain textBox");
        domainInput.clearAndSetText(domain);
    }

    public void setRandomDomainEnd() {
        logger.info("Click on domain end img");
        dropDownImg.clickElement();

        logger.info("Initialization of dropDownElementsList");
        ElementsList dropDownElementsList = new ElementsList(DROPDOWN_ELEMENT_XPATH);
        logger.info("Get random element's index");
        int index = user.getRandomDomainIndex(dropDownElementsList);
        logger.info("Click on random element");
        dropDownElementsList.getElement(index).click();
    }

    public void clickOnAcceptingTermsCheckBox() {
        logger.info("Click on acceptingTermsCheckBox");
        acceptingTermsCheckBox.clickElement();
    }

    public void clickOnNextButton() {
        logger.info("Click on next button");
        nextButton.clickElement();
    }

    public void clickOnAcceptCookies() {
        logger.info("Click on accepting cookies button");
        acceptingCookiesButton.clickElement();
    }

    public boolean isCookieFormEnable() {
        logger.info("Initialisation of cookiesButtonList");
        ElementsList cookiesButtonList = new ElementsList(ACCEPT_COOKIES_BUTTON);
        logger.info("Checking the availability of a cookie form");
        return !(cookiesButtonList.getSize() == 0);
    }

    public void clickOnHideHelpFormButton() {
        logger.info("Click on hide help's form button");
        hideHelpFormButton.clickElement();
    }

    public boolean isHelpFormEnable() {
        logger.info("Checking the availability of a help form");
        howCanWeHelpLabel.findElement();
        while (howCanWeHelpLabel.isElementDisplayed()){
        }
        return howCanWeHelpLabel.isElementDisplayed();
    }

    public boolean isTimerStartsFromZero(String time) {
        logger.info("Initialization of timer label");
        Label timerLabel = new Label(TIMER_LABEL_XPATH);
        logger.info("Checking that timer start from " + time);
        return timerLabel.getElementText().matches(time);
    }

}
