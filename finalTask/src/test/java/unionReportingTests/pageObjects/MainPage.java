package unionReportingTests.pageObjects;

import elements.Button;
import elements.Label;
import logger.Logger;
import pages.BasePage;
import unionReportingTests.constants.Sign;
import unionReportingTests.models.TestModel;

public class MainPage extends BasePage {
    private static final Logger LOG = new Logger(MainPage.class);

    private static final String ADD_BUTTON_XPATH = "//button[@data-target='#addProject']";
    private static final String PROJECT_BUTTON_XPATH_FORMAT = "//a[contains(text(),'%s')]";
    private static final String VERSION_LABEL_XPATH = "//footer//span";

    private Button addProjectButton = new Button(ADD_BUTTON_XPATH, "addProjectButton");
    private Label versionLabel = new Label(VERSION_LABEL_XPATH, "versionLabel");
    private Button prjctButton;

    public MainPage(String url) {
        super(ADD_BUTTON_XPATH, url);
        LOG.info("Initialization of main page class");
    }

    private void setSpecificProjectButton(String prjct) {
        prjctButton = new Button(String.format(PROJECT_BUTTON_XPATH_FORMAT, prjct),
                                                                "prjctButton");
    }

    public void clickOnProject(String prjctName) {
        setSpecificProjectButton(prjctName);
        prjctButton.clickElement();
    }

    public void clickOnAddProjectButton() {
        addProjectButton.clickElement();
    }

    public String getProjectId(String prjctName) {
        setSpecificProjectButton(prjctName);
        String href = prjctButton.getHref();
        return href.substring(href.lastIndexOf(Sign.EQUAL_SIGN) + Sign.EQUAL_SIGN.length());
    }

    public Boolean isPageContainsCorrectVar(String expectedVersion) {
        String version = versionLabel.getElementText();
        version = version.substring(version.indexOf(Sign.COLON) + Sign.COLON.length());
        version = version.replaceAll(Sign.SPACE, Sign.EMPTY);

        return version.equals(expectedVersion);
    }

}
