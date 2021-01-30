package fileUtils;

import browsersUtils.WebDriverInitialization;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import elements.Img;
import logger.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class FileUtil {
    private static final Logger LOG = new Logger(FileUtil.class);
    private String filePath;
    public FileUtil (String path) {
        filePath = path;
    }

    public String read() {
        try {
            String result = "";
            FileReader inputFile = new FileReader(filePath);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            String line;
            while ((line = bufferReader.readLine()) != null) {
                result += line + "\n";
            }
            bufferReader.close();
            return result;
        } catch (Exception e) {
            LOG.error(e);
            return null;
        }
    }

    public static void writePngFromNet(String url, String pathToImg) {
        try {
            URL imageURL = new URL(url);
            BufferedImage saveImage = ImageIO.read(imageURL);
            ImageIO.write(saveImage, "png", new File(pathToImg));
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    public static File saveFileFromBase(String base64Image, String pathToNewImg) {
        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
        File file = new File(pathToNewImg);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(imageBytes);
        } catch (IOException e) {
            LOG.error(e);
        }
        return file;
    }

    public static byte[] getByteFromImgFile(File file) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
            BufferedImage image = ImageIO.read(file);
            ImageIO.write(image, "jpg", baos);
            baos.flush();
            return Base64.decode(Base64.encode(baos.toByteArray()));
        } catch (Exception ex) {
            LOG.error(ex);
            return null;
        }
    }

    public static void saveImgWithUI(WebElement img, String path) {
        StringSelection stringSelection = new StringSelection(path);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        WebDriver driver = WebDriverInitialization.getInstance();
        Actions action = new Actions(driver);
        action.contextClick(img).build().perform();
        try {
            Robot robot = new Robot();
            goToSaveAsOption(robot);
            pressAndReleaseEnter(robot);

            insertFilePath(robot);
            pressAndReleaseEnter(robot);

            goToSaveButton(robot);
            pressAndReleaseEnter(robot);
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    private static void goToSaveAsOption(Robot robot) {
        try {
            for (int i  = 0; i < 7; i++) {
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);
            }
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    private static void goToSaveButton(Robot robot) {
        try {
            for (int i = 0; i < 8; i++) {
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
            }
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    private static void pressAndReleaseEnter(Robot robot) {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    private static void insertFilePath(Robot robot) {
        try {
            for (int i = 0; i < 6; i++) {
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
            }
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
        } catch (Exception e) {
            LOG.error(e);
        }
    }
}
