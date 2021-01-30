package iframe;

import autoTestFramework.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import iframe.pageObjects.IFrame;
import iframe.pageObjects.MainPage;

/**
 * @author Kirill Uskov
 * @version 1.3
 *
 * Тестовый класс для тестирования iFrame
 */

public class IFrameTestCase extends BaseTest {
    private static final String TITLE_TEXT_OF_PAGE = "An iFrame containing the TinyMCE WYSIWYG Editor";

    private MainPage mainPage;
    private IFrame iFrame;

    @BeforeClass
    public void initialisation() {
        mainPage = new MainPage(siteUrl);
        iFrame = new IFrame();
    }

    @Test
    public void enterTextTestCase() {
        mainPage.openPage();
        Assert.assertEquals(mainPage.getPagesText(), TITLE_TEXT_OF_PAGE, "Wrong page!");

        iFrame.deleteAllText();
        Assert.assertNotNull(iFrame.setRandomText(), "Empty textBox");

        iFrame.boldButtonClick();
        Assert.assertTrue(iFrame.isStringBold());
    }

}
