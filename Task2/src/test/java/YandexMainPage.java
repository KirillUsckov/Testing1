import Reader.StringsReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class YandexMainPage {
    private final String YANDEX_MAIN_TITLE = "Яндекс";

    private static YandexMainPage yandexMainPage;
    private String yandexMainPageLink;
    private String loginButtonXpath;
    private String marketLink;
    private String marketURL;
    private String profileIconPath;
    private String logoutButtonXpath;

    private String inputXPath = "//input[@class='input__control input__input mini-suggest__input']";

    private WebDriver driver;

    private YandexMainPage(Properties properties, WebDriver driver) {
        StringsReader stringsReader = StringsReader.getInstance();
        yandexMainPageLink = stringsReader.getSiteUrl();

        loginButtonXpath = properties.getProperty("loginInMailBoxButton");
        marketLink = properties.getProperty("marketPageLInk");
        profileIconPath = properties.getProperty("profileIconPath");
        logoutButtonXpath = properties.getProperty("logoutButtonXpath");
        this.driver = driver;
    }

    public static YandexMainPage getInstance(Properties properties, WebDriver driver) {
        if (yandexMainPage == null){
            yandexMainPage = new YandexMainPage(properties, driver);
        }
        return yandexMainPage;
    }

    public Boolean openYandex() {
        driver.get(yandexMainPageLink);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement input = new WebDriverWait(driver, 10)
                .until(driver -> driver.findElement(By.xpath(inputXPath)) );
        return input != null;
    }

    public String sighIn() {
        String url = driver.findElement(By.xpath(loginButtonXpath)).getAttribute("href");
        return url;
    }

    public String goToMarket() {
        if (marketURL == null) {
            WebElement marketNavigation = new WebDriverWait(driver, 10)
            .until(driver -> driver.findElement(By.xpath(marketLink)) );
            marketURL = marketNavigation.getAttribute("href");
        }
        driver.get(marketURL);
        return driver.getTitle();
    }

    public void logout() {
        WebElement profile = new WebDriverWait(driver, 10)
                .until(driver -> driver.findElement(By.xpath(profileIconPath)) );
        profile.click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement logoutButton = driver.findElement(By.xpath(logoutButtonXpath));
        logoutButton.click();
    }

}
