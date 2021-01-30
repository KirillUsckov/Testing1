import CSVReaderAndWriter.CSVReader;
import CSVReaderAndWriter.CSVWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class YandexMarketPage {
    private static YandexMarketPage yandexMarketPage;
    private WebDriver driver;
    private List<WebElement> catalogItems;
    private List<String> titleAvailableMenuElements = new ArrayList<>();

    private final int TWENTY_SECONDS = 20;

    private String catalogXpath;
    private String menuElementsXpath;
    private String catalogItemsXpath;
    private String csvFilePath = "src/test/productCatalog.csv";
    private String elementForWait = "//div[@data-zone-name='category-link']//a";

    private YandexMarketPage(Properties properties, WebDriver driver) {
        this.driver = driver;
        catalogItemsXpath = properties.getProperty("catalogItemsXpath");
        catalogXpath = properties.getProperty("catalogXpath");
        menuElementsXpath = properties.getProperty("menuElementsXpath");
    }

    public static YandexMarketPage getInstance(Properties properties, WebDriver driver) {
        if (yandexMarketPage == null) {
            yandexMarketPage = new YandexMarketPage(properties, driver);
        }
        return yandexMarketPage;
    }

    public String getMenu() {
        int currentCode;

        WebDriverWait myWaitVar = new WebDriverWait(driver, TWENTY_SECONDS);
        myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementForWait)));

        List<WebElement> menuElements = driver.findElements(By.xpath(menuElementsXpath));
        List<WebElement> availableMenuElements = new ArrayList<>();

        for (WebElement element: menuElements) {
            if (element.getText().length() != 0) {
                availableMenuElements.add(element);
                String title = TitleCheck.getExpectedTitleForMarket(element.getText());
                titleAvailableMenuElements.add(title);
            }
        }

        //Получение индекса рандомного пункта меню
        currentCode = new Random().nextInt(availableMenuElements.size());
        WebElement menuItem = availableMenuElements.get(currentCode);

        String linkName = menuItem.getText();
        String url = menuItem.getAttribute("href");
        driver.get(url);

        System.out.println(linkName);
        return linkName;
    }

    public void setProductCatalog() {
        WebElement getCatalogItem = new WebDriverWait(driver, TWENTY_SECONDS)
                .until( driver1 -> driver.findElement(By.xpath(catalogXpath)) );
        getCatalogItem.click();

        catalogItems = driver.findElements(By.xpath(catalogItemsXpath));
        CSVWriter.writeDocument(catalogItems, csvFilePath);
    }

    public Boolean checkProductCatalog() {
        ArrayList<String> productCatalog = CSVReader.readDocument(csvFilePath);

        int size = titleAvailableMenuElements.size();

        for (String el: titleAvailableMenuElements) {
            for (String catalogElement: productCatalog) {
                if (catalogElement.contains(el)) {
                    size--;
                }
            }
        }
        return size == 0;
    }

}
