import BrowsersFactory.BrowserMaker;
import BrowsersFactory.ChromeFactoryMaker;
import BrowsersFactory.FireFoxFactoryMaker;

import Reader.StringsReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 *  Тест-кейс для проверки:
 *  - входа в учетную запись yandex
 *  - перехода на страницу yandex.market
 *  - перехода на различные страницы-категории yandex.market
 *  - выхода из учетной записи yandex
 *
 *  Браузер, ОС, email и пароль для авторизации задается через файл strings.xml в директории resources
 *  @verion 1.08
 *  @aurhor Kirill Uskov
 */

public class TestYandex {

    private final String YANDEX_EXCEPTION = "YOU DID NOT GO TO YANDEX";
    private final String LOGIN_EXCEPTION = "YOU DID NOT GO TO THE AUTHORIZATION TAB";
    private final String MARKET_PAGE_EXCEPTION = "YOU DID NOT GO TO A YANDEX MARKET PAGE";
    private final String DIFFERENT_PAGE_EXCEPTION = "YOU ARE IN THE WRONG CATEGORY";
    private final String NOT_ENOUGH_CATALOG_ITEMS = "NOT ALL TABS ARE FOUND";

    private final String LOGIN_TITLE = "Авторизация";
    private final String YANDEX_MARKET = "market.yandex";

    private WebDriver driver;
    private StringsReader stringsReader;

    private YandexMainPage yandexMainPage;
    private YandexLoginPage yandexLoginPage;
    private YandexMarketPage yandexMarketPage;

    private int codeOfOS;

    @BeforeTest
    void setUp() {
        stringsReader = StringsReader.getInstance(); //читаем файл "strings.xml"
        stringsReader.getStringFromXML();
        try {
            setBrowser(stringsReader.getBrowser());

            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(stringsReader.getPropertyDirectoryPath());
            properties.load(fileInputStream);

            yandexMainPage = YandexMainPage.getInstance(properties, driver);
            yandexLoginPage = YandexLoginPage.getInstance(properties, driver);
            yandexMarketPage = YandexMarketPage.getInstance(properties, driver);
        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + stringsReader.getPropertyDirectoryPath() + " не обнаружен");
            e.printStackTrace();
        }
    }

    private void setBrowser(String browserName){
        BrowserMaker maker = null;
        switch (browserName) {
            case "Chrome":
                maker = new ChromeFactoryMaker();
                break;
            case "FireFox":
                maker = new FireFoxFactoryMaker();
        }
        driver = maker.createBrowser(stringsReader.getCodeOS()).getBrowserDriver();
    }

    @Test
    public void loginGoMarketLogoutTestCase() {
        //Переход на главную страницу
        Assert.assertTrue(yandexMainPage.openYandex(), YANDEX_EXCEPTION);
        driver.get(yandexMainPage.sighIn());

        //Переход на страницу авторизации
        Assert.assertTrue(driver.getTitle().contains(LOGIN_TITLE), LOGIN_EXCEPTION);
        yandexLoginPage.enterUserName(stringsReader.getEmail());
        yandexLoginPage.clickLoginButton();

        //Переход на страницу ввода пароля
        Assert.assertEquals(driver.getTitle(), LOGIN_TITLE, LOGIN_EXCEPTION);
        yandexLoginPage.enterPassword(stringsReader.getPassword());
        yandexLoginPage.clickLoginButton();

        Assert.assertTrue(yandexLoginPage.checkLogin(yandexMainPage, stringsReader.getEmail()));

        //Переход на страницу Маркета
        yandexMainPage.goToMarket();
        Assert.assertTrue(driver.getCurrentUrl().contains(YANDEX_MARKET), MARKET_PAGE_EXCEPTION);

        String expectedTitle = TitleCheck.getExpectedTitle(yandexMarketPage.getMenu());
        System.out.println(expectedTitle);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println(driver.findElement(By.tagName("h1")).getText() + " - " + expectedTitle);
        Assert.assertTrue(driver.findElement(By.tagName("h1")).getText().contains(expectedTitle), DIFFERENT_PAGE_EXCEPTION);

        //Переход на страницу Маркета
        yandexMainPage.goToMarket();
        yandexMarketPage.setProductCatalog();

        //Проверка на наличие вкладок
        Assert.assertTrue(yandexMarketPage.checkProductCatalog(), NOT_ENOUGH_CATALOG_ITEMS);

        yandexMainPage.openYandex();
        yandexMainPage.logout();

        driver.quit();
    }


}
