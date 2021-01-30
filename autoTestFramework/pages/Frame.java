package pages;

import elements.Button;
import elements.Img;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class Frame extends BasePage {


    private String id;
    private String locator;

    public Frame(String id, String locator) {
        this.id = id;
        this.locator = locator;
    }

    public void switchToFrame() {
        webDriver.switchTo().frame(id);
    }

    public boolean isFrameClosed(String classValue) {
        Img img = new Img(locator, "Img");
        img.findElementByTagName("body");
        String actualClass = img.getElementAttributeValue("class");
        return actualClass.equals(classValue);
    }

    public void switchToDefault() {
        webDriver.switchTo().defaultContent();
    }

    public void closePopUp() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("$('#addProject').modal('hide');");
    }
}
