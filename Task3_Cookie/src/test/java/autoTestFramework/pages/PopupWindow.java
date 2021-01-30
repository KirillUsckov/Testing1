package autoTestFramework.pages;

import autoTestFramework.constants.CommonConstants;
import autoTestFramework.stringsUtils.StringsReader;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * The class is responsible for initializing the PopupWindow and for
 * implementing the unique page functionality
 */

public class PopupWindow extends BasePage {


    private final String FIRE_FOX_TEXT = "FireFox";
    private final String BROWSER = "browser";
    private final String WAITING_TIME = "waitingTime";

    private Logger logger;

    public PopupWindow(String locator) {
        super(locator);
        logger = Logger.getLogger(PopupWindow.class);
    }

    public void popUpPressEnter() {
        StringsReader stringsReader = new StringsReader(CommonConstants.SETTINGS_PATH);
        int waitingTime = Integer.parseInt(stringsReader.getCustomElement(WAITING_TIME));

        Robot robot;
        try {
            robot = new Robot();

            if (stringsReader.getCustomElement(BROWSER).contains(FIRE_FOX_TEXT)) {

                robot.delay(waitingTime);
                logger.info("Switch to the confirmation button");
                robot.keyPress(KeyEvent.VK_LEFT);
            }

            robot.delay(waitingTime);
            logger.info("Press enter");
            robot.keyPress(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            logger.warn(e);
        }
    }

}
