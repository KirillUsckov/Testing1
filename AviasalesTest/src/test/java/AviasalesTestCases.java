import AutoTest.BrowsersUtils.WebDriverInitialization;
import AutoTest.StringsUtils.StringsReader;

import Constants.CommonConstants;
import Constants.TestData;

import PageObjects.AviasalesMainPage;
import PageObjects.AviasalesSearchingPage;
import locators.MainPage;

import locators.SearchingPage;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * @author Kirill Uskov
 * @version 1.5
 *
 * Test class for checking tickets prices
 */

public class AviasalesTestCases {
    private final String TOP_ASSERT_TEXT = "POSITION CHECKING FAILED: The ticket wasn't the first!";
    private final String BOTTOM_ASSERT_TEXT = "POSITION CHECKING FAILED: The ticket wasn't the last!";

    private static AviasalesMainPage mainPage;
    private static AviasalesSearchingPage searchingPage;

    @DataProvider(name = "testDataForFindTicket")
    public Object[][] setTestDataForFindTicket() {
        return new Object[][] { {TestData.MINSK, TestData.ISTANBUL, true},
                                    {TestData.ISTANBUL, TestData.MINSK, false} };
    }

    @DataProvider(name = "testDataForFindDirectTicket")
    public Object[][] setTestDataForFindDirectTicket() {
        return new Object[][] { {TestData.MINSK, TestData.ISTANBUL, false},
                            {TestData.ISTANBUL, TestData.TUNIS, false} };
    }

    @BeforeSuite
    public void setUp() {

        WebDriverInitialization.setBrowser(CommonConstants.SETTINGS_PATH);
    }

    @BeforeMethod
    public void prepare() {
        StringsReader strReader = new StringsReader(CommonConstants.SETTINGS_PATH);
        String url = strReader.getCustomElement(CommonConstants.SITE_URL_TEXT);
        mainPage = new AviasalesMainPage(MainPage.MAIN_PAGE_LOCATOR, url);

        searchingPage = new AviasalesSearchingPage(SearchingPage.FINISHED_LOADER_XPATH);

        mainPage.openPage();
    }

    @Test(groups = {"findTicket"}, dataProvider = "testDataForFindTicket")
    public void testCaseFindCheapestTicket(String departureCity, String destinationCity,
                                                                boolean shouldCheckCheckbox){
        FirstStep.start(departureCity, destinationCity, shouldCheckCheckbox, mainPage, searchingPage);

        searchingPage.findTickedWithLowestPrice();
        Assert.assertTrue(searchingPage.checkElementPosition(CommonConstants.TOP), TOP_ASSERT_TEXT);
    }

    @Test(groups = {"findDirectTicket"}, dataProvider = "testDataForFindDirectTicket")
    public void testCaseFindCheapestDirectTicket(String departureCity, String destinationCity,
                                                                    boolean shouldCheckCheckbox){
        FirstStep.start(departureCity, destinationCity, shouldCheckCheckbox, mainPage, searchingPage);

        searchingPage.clickDirectButton();
        searchingPage.findTickedWithLowestPrice();
        Assert.assertTrue(searchingPage.checkElementPosition(CommonConstants.BOTTOM), BOTTOM_ASSERT_TEXT);

    }

    @AfterSuite
    public void exit() {

        WebDriverInitialization.quiteBrowser();
    }

}
