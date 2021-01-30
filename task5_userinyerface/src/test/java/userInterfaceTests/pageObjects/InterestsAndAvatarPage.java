package userInterfaceTests.pageObjects;

import downloadUtil.UploadFile;
import elements.Button;
import elements.CheckBox;
import elements.Label;
import elementsUtils.ElementsList;
import logger.Logger;
import pages.BasePage;

import java.util.List;

public class InterestsAndAvatarPage extends BasePage {

    private static final String UPLOAD_AVATAR_LABEL_XPATH = "//a[@class='avatar-and-interests__upload-button']";
    private static final String INTEREST_CHECKBOXE_XPATH = "//span[@class='checkbox__box']";

    private Label uploadLabel = new Label(UPLOAD_AVATAR_LABEL_XPATH);
    private ElementsList interestsChckBx;
    private Button nextButton = new Button("//button[contains(text(), 'Next')]");
    private Logger logger;

    public InterestsAndAvatarPage() {
        super(UPLOAD_AVATAR_LABEL_XPATH);
        logger= new Logger(InterestsAndAvatarPage.class);
    }

    public void clickOnUploadLabel() {
        logger.info("Initialization of upload label");
        logger.info("Click on upload label");
        uploadLabel.clickElement();
    }

    public void uploadAvatar(String avatarLocation) {
        logger.info("Set avatar file path");
        String filePath = UploadFile.getProjectFilePath("task5_userinyerface",
                avatarLocation);
        UploadFile.uploadFileWithWindow(filePath);
    }

    public void setInterests(int interestsAmount) {
        if (interestsAmount == 0) {
            logger.error("Interests amount = 0");
        } else {
            interestsChckBx = new ElementsList(INTEREST_CHECKBOXE_XPATH);
            /* maxIndex - переменная, обозначающая чекбокс, отвечающий за очистку интересов */
            int maxIndex = interestsChckBx.getSize() - 1;

            clearInterests(interestsChckBx, maxIndex);

            clickOnRandomInterests(interestsChckBx, interestsAmount, maxIndex);
        }
    }

    private void clearInterests(ElementsList interestsChckBx, int maxIndex) {
        logger.info("Click on clear interest button");
        CheckBox interestCheckBox = new CheckBox(interestsChckBx.getElement(maxIndex));
        interestCheckBox.clickElement();
    }

    private void clickOnRandomInterests(ElementsList interestsChckBx, int interestsAmount, int maxIndex) {
        logger.info("Set random checkBoxes' indexes");
        List<Integer> indexes = interestsChckBx.getRandomElementsIndexes(interestsAmount, maxIndex);
        logger.info("Click on random checkBoxes");
        for (int i = 0; i < indexes.size(); i++) {
            CheckBox interestCheckBox = new CheckBox(interestsChckBx.getElement(indexes.get(i)));
            interestCheckBox.clickElement();
        }
    }

    public void clickNextButton() {
        logger.info("Click on nextButton");
        nextButton.clickElement();
    }

}
