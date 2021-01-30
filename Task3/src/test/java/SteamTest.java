import AutoTest.BrowsersFactory.WebDriverInitialization;
import AutoTest.DownloadUtil.DownloadFileWithURL;
import AutoTest.StringsUtil.SetGenre;
import AutoTest.StringsUtil.StringsReader;
import org.testng.annotations.*;

/**
 * @author Kirill Uskov
 * @version 12
 *
 * Class is used to test 3 test cases for Steam's site
 */
public class SteamTest {
    private final String SETTINGS_LINK = "src/main/resources/settings.xml";
    private final String TEST_DATA_LINK = "src/main/resources/testData.xml";

    private final String MAX = "maxSorting";
    private final String MIN = "minSorting";
    private final String AGE_CHECK = "agecheckText";

    private MainPage mainPage;
    private InstallPage installPage;
    private GenrePage genrePage;
    private ProofOfAgePage proofOfAgePage;
    private GamePage gamePage;

    private DownloadFileWithURL fileDownloader;
    private StringsReader reader;
    private SetGenre setter;

    /**
     * Set StringsReader, Browser, SetGenre, all pages.
     * Also set localisation for site.
     */

    @BeforeTest
    public void setUp() {
        reader = new StringsReader(TEST_DATA_LINK);
        WebDriverInitialization.setBrowser();
        setter = SetGenre.getInstance(SETTINGS_LINK, TEST_DATA_LINK);

        mainPage = new MainPage();
        fileDownloader = new DownloadFileWithURL(SETTINGS_LINK);

        installPage = new InstallPage();
        genrePage = new GenrePage();
        proofOfAgePage = new ProofOfAgePage();
        gamePage = new GamePage();

        mainPage.openPage();
        mainPage.setLocalisation();
    }

    /**
     * Used to avoid errors related to page reloading
     */
    @BeforeMethod
    public void prepare() {

        mainPage.reloadPage();
    }

    /**
     * Steam app download
     * - Open http://store.steampowered.com/;
     * - Click “Install Steam” button;
     * - Click “Install Steam Now” button -> download steam app.
     */

    @Test (groups = {"SteamAppDownload"})
    public void testCaseSteamAppDownload() {
        mainPage.openPage();
        mainPage.waitingForOpen();
        mainPage.goToInstallPage();

        installPage.waitingForOpen();
        installPage.downloadSteamSetup();

        fileDownloader.isDownload();
    }


    /**
     * Highest discount calculation check
     * - Open http://store.steampowered.com/;
     * - Select Games -> Action in the top menu;
     * - Navigate to “Top Selling” tab;
     * - Click the game with the highest discount on the 1 page of the games list.
     *      Enter correct age on the “Rated content” page if it’s shown;
     * - Check that game discount rate, initial and discounted prices are the same as on the step 4.
     */

    @Test (groups = {"TestSuitDiscountCalculationCheck"})
    public void testCaseHighestDiscountCalculationCheck() {
        mainPage.openPage();
        mainPage.waitingForOpen();
        mainPage.goToSelectGames(setter.getActionGenre());

        genrePage.goToTopSellers();
        genrePage.waitingForOpen();
        genrePage.getDiscountGame(reader.getCustomElement(MAX));

        proofOfAge();

        gamePage.setPrices();
        gamePage.checkPrices(genrePage.gameDiscountRate,genrePage.gameDiscountedPrice, genrePage.gameInitialPrice);
    }

    /**
     * Lowest discount calculation check
     * - Open http://store.steampowered.com/;
     * - Select Games -> Indie in the top menu;
     * - Navigate to “Top Selling” tab;
     * - Click the game with the lowest discount (but discount > 0) on the 1 page of the games list.
     *      Enter correct age on the “Rated content” page if it’s shown;
     * - Check that game discount rate, initial and discounted prices are the same as on the step 4.
     */
    @Test (groups = {"TestSuitDiscountCalculationCheck"})
    public void testCaseLowestDiscountCalculationCheck() {
        mainPage.openPage();
        mainPage.waitingForOpen();
        mainPage.goToSelectGames(setter.getIndieGenre());

        genrePage.goToTopSellers();
        genrePage.waitingForOpen();
        genrePage.getDiscountGame(reader.getCustomElement(MIN));

        proofOfAge();

        gamePage.setPrices();
        gamePage.checkPrices(genrePage.gameDiscountRate,genrePage.gameDiscountedPrice, genrePage.gameInitialPrice);
    }

    private void proofOfAge() {
        if (proofOfAgePage.getUrl().contains(reader.getCustomElement(AGE_CHECK))) {
            proofOfAgePage.selectBirthdayYear();
            proofOfAgePage.viewPage();
        }
    }

    @AfterTest
    public void setOut() {

        installPage.closePage();
    }

}
