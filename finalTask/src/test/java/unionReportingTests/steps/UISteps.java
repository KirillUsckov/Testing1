package unionReportingTests.steps;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import fileUtils.FileUtil;

import logger.Logger;
import unionReportingTests.models.TestModel;
import unionReportingTests.pageObjects.MainPage;
import unionReportingTests.pageObjects.MainPageIFrame;
import unionReportingTests.pageObjects.SpecificProjectPage;
import unionReportingTests.pageObjects.TestPage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

public class UISteps {
    private static final Logger LOG = new Logger(UISteps.class);
    private static TestPage page = new TestPage();

    private UISteps() {}

    public static void addNewProject(MainPage mainPage, MainPageIFrame iFrame, String prjctName) {
        SpecificProjectPage specificProjectPage = new SpecificProjectPage();
        specificProjectPage.goToHome();
        mainPage.clickOnAddProjectButton();
        iFrame.switchToFrame();
        iFrame.setProjectName(prjctName);
        iFrame.clickOnProjectSaveButton();
        iFrame.closePopUp();
    }

    public static boolean isLogCorrect(TestModel test) {
        String expectedLog = test.getLog();
        String actualLog = page.getPageLogText();
        boolean result = expectedLog.equals(actualLog);
        if (!result) {
            LOG.error("EXPECTED LOG: " + expectedLog +
                            "\nACTUAL LOG: " + actualLog);
        }
        return result;
    }

    public static boolean isStartTimeCorrect(TestModel test) {
        String expectedStartTime = test.getStartTime();
        String actualStartTime = page.getStartTimeLabel().getElementText();
        boolean result = actualStartTime.contains(expectedStartTime);
        if (!result) {
            LOG.error("EXPECTED START TIME: " + expectedStartTime +
                            "\nACTUAL START TIME: " + actualStartTime);
        }
        return result;
    }

    public static boolean isEndTimeCorrect(TestModel test) {
        String expectedEndTime = test.getEndTime();
        String actualEndTime = page.getEndTimeLabel().getElementText();
        boolean result = actualEndTime.contains(expectedEndTime);
        if (!result) {
            LOG.error("EXPECTED START TIME: " + expectedEndTime +
                            "\nACTUAL START TIME: " + actualEndTime);
        }
        return result;
    }

    public static boolean isProjectNameCorrect(TestModel test) {
        String expectedPrjctName = test.getProjectName();
        String actialProjectName = page.getProjectNameLabel().getElementText();
        boolean result = expectedPrjctName.equals(actialProjectName);
        if (!result) {
            LOG.error("EXPECTED START TIME: " + expectedPrjctName +
                            "\nACTUAL START TIME: " + actialProjectName);
        }
        return result;
    }

    public static boolean isTestNameCorrect(TestModel test) {
        String expectedTestName = test.getTestName();
        String actualTestName = page.getMethodNameLabel().getElementText();
        boolean result = expectedTestName.equals(actualTestName);
        if (!result) {
            LOG.error("EXPECTED START TIME: " + expectedTestName +
                            "\nACTUAL START TIME: " + actualTestName);
        }
        return result;
    }

    public static boolean isStatusCorrect(TestModel test) {
        String expectedStatus = test.getTextStatus();
        String actualStatus = page.getStatusLabel().getElementText();
        boolean result = expectedStatus.equals(actualStatus);
        if (!result) {
            LOG.error("EXPECTED START TIME: " + expectedStatus +
                            "\nACTUAL START TIME: " + actualStatus);
        }
        return result;
    }

    public static boolean isEnvironmentCorrect(TestModel test) {
        String expectedEnv = test.getEnvironment();
        String actualEnv = page.getEnvironment().getElementText();
        boolean result = expectedEnv.equals(actualEnv);
        if (!result) {
            LOG.error("EXPECTED START TIME: " + expectedEnv +
                            "\nACTUAL START TIME: " + actualEnv);
        }
        return result;
    }

    public static boolean isImgCorrect(TestModel test) {
        boolean result;
        String base64Image = page.getPicture();
        String imgPath = test.getScreenshot().getPath();
        String pathToNewImg = imgPath.replace(".", "1.");
        File newImg = FileUtil.saveFileFromBase(base64Image, pathToNewImg);

        byte[] newImgByteArray = FileUtil.getByteFromImgFile(newImg);
        byte[] expectedImgByteArray = FileUtil.getByteFromImgFile(test.getScreenshot());
        result = Arrays.equals(expectedImgByteArray, newImgByteArray);
        if (!result) {
            LOG.error("EXPECTED IMG BYTE ARRAY: " + Arrays.toString(newImgByteArray) +
                    "\nACTUAL IMG BYTE ARRAY: " + Arrays.toString(expectedImgByteArray));
        }
        newImg.delete();
        return result;
    }

    public static String getProjectId(MainPage page, String projectName) {
        return page.getProjectId(projectName);
    }
}
