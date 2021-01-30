package autoTestFramework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Frame extends BasePage {
    private WebElement element;
    private String xPath, id;

    public Frame(String xPath) {
        this.xPath = xPath;
    }

    public void switchToFrame() {
        getFrameId();
        webDriver.switchTo().frame(id);
    }

    public void switchToDefault() {
        webDriver.switchTo().defaultContent();
    }

    public void getFrameId() {
        element = webDriver.findElement(By.xpath(xPath));
        id = element.getAttribute("id");
    }
}
