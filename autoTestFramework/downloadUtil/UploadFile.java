package downloadUtil;

import browsersUtils.WebDriverInitialization;
import elements.BaseElement;
import elements.Label;
import elements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UploadFile {

    private static final String UPLOAD_INPUT = "//input[@type='file']";

    public static String getProjectFilePath(String projectName, String projectFilePath) {
        File currentClass = null;
        try {
            currentClass = new File(URLDecoder.decode(UploadFile.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String classDirectory = currentClass.getParent();
        int indexOfProjectNamesEnd = classDirectory.indexOf(projectName) + projectName.length();
        classDirectory = classDirectory.substring(0, indexOfProjectNamesEnd);
        File file= new File(classDirectory + projectFilePath);
        String absolutePath = file.getAbsolutePath();

        return absolutePath;
    }

    public static void uploadFile(String path) {
        WebDriver webDriver = WebDriverInitialization.getInstance();
        WebElement upload = webDriver.findElement(By.xpath(UPLOAD_INPUT));
        String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";

        ((JavascriptExecutor) webDriver).executeScript(js, upload);
        upload.sendKeys(path);
    }

    public static void uploadFileWithWindow(String filePath) {
        try {
            Thread.sleep(2000);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(new StringSelection(filePath), null);

            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);

            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
