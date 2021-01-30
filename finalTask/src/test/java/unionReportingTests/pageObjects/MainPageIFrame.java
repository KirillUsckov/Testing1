package unionReportingTests.pageObjects;

import elements.Button;
import elements.TextBox;
import pages.Frame;

public class MainPageIFrame extends Frame {
    private static final String IFRAME_ID = "addProjectFrame";
    private static final String MODAL_WINDOW_CLASS_TEXT = "modal-open";
    private static final String SAVE_BUTTON_XPATH = "//button[@type='submit']";
    private static final String PROJECT_NAME_TEXTBOX_XPATH = "//input[@name='projectName']";
    private Button saveProjectButton = new Button(SAVE_BUTTON_XPATH, "saveProjectButton");
    private TextBox projectNameInput = new TextBox(PROJECT_NAME_TEXTBOX_XPATH, "projectNameInput");


    public MainPageIFrame() {
        super(IFRAME_ID, SAVE_BUTTON_XPATH);
    }

    public void clickOnProjectSaveButton() {
        saveProjectButton.clickElement();
    }

    public void setProjectName(String prjctName) {
        projectNameInput.clickElement();
        projectNameInput.setElementText(prjctName);
    }

    @Override
    public boolean isFrameClosed(String classValue) {
        return super.isFrameClosed(classValue);
    }
}
