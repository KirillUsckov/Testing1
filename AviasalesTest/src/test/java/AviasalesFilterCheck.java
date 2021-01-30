import AutoTest.BrowsersUtils.WebDriverInitialization;
import AutoTest.StringsUtils.StringsReader;
import AutoTest.WaitingUtils.Waiting;

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
 * Test class for checking filter
 */

public class AviasalesFilterCheck {

    private final String FILTER_ASSERT_TEXT = "FILTER CHECKING FAILED: Not all tickets have baggage!";

    private AviasalesMainPage mainPage;
    private AviasalesSearchingPage searchingPage;

    @DataProvider(name = "checkBaggageFilter")
    private Object[][] setDataForCheckBaggageFilter() {
        return new Object[][] { {TestData.MINSK, TestData.ISTANBUL, true} };
    }

    @BeforeSuite
    public void setUp() {
        WebDriverInitialization.setBrowser(CommonConstants.SETTINGS_PATH);

    }

    @BeforeMethod
    public void prepare() {
        Waiting.waitFiveSeconds();
        StringsReader strReader = new StringsReader(CommonConstants.SETTINGS_PATH);
        String url = strReader.getCustomElement("siteUrl");

        mainPage = new AviasalesMainPage(MainPage.MAIN_PAGE_LOCATOR, url);
        searchingPage = new AviasalesSearchingPage(SearchingPage.FINISHED_LOADER_XPATH);

        mainPage.openPage();
    }

    @Test(groups = {"checkBaggageFilter"}, dataProvider = "checkBaggageFilter")
    public void testCaseBaggageCheck(String departureCity, String destinationCity,
                                                        boolean shouldCheckCheckbox){
        FirstStep.start(departureCity, destinationCity, shouldCheckCheckbox, mainPage, searchingPage);

        searchingPage.clickBaggageFilterButton();
        searchingPage.setCheckboxState();
        Assert.assertTrue(searchingPage.checkFiltredTickets(), FILTER_ASSERT_TEXT);
    }

    @AfterClass
    public void exit() {
        WebDriverInitialization.quiteBrowser();
    }

}
