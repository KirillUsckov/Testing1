package autoTestFramework.pages;

public class Frame extends BasePage {

    private String id;

    public Frame(String id) {
        this.id = id;
    }

    public void switchToFrame() {
        webDriver.switchTo().frame(id);
    }

    public void switchToDefault() {
        webDriver.switchTo().defaultContent();
    }
}
