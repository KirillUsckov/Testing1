package unionReportingTests.pageObjects;

import elements.Img;
import elements.Label;
import elementsUtils.ElementsList;
import fileUtils.FileUtil;
import pages.BasePage;

public class TestPage extends BasePage {
    private static final String ATTACHMENTS_XPATH = "//div[@class='col-md-8']//td[1]";
    private static final String START_TIME_LABEL_XPATH = "//p[contains(text(),'Start time:')]";
    private static final String END_TIME_LABEL_XPATH = "//p[contains(text(),'End time:')]";
    private static final String PROJECT_NAME_LABEL_XPATH = "//div[@class='panel panel-default']//div[2]//div[1]//p[1]";
    private static final String METHOD_NAME_LABEL_XPATH = "//div[@class='panel panel-default']//div[2]//div[2]//p[1]";
    private static final String STATUS_LABEL_XPATH = "//div[@class='panel panel-default']//div[2]//span";
    private static final String ENVIRONMENT_LABEL_XPATH = "//div[@class='panel panel-default']//div[2]//div[6]//p[1]";
    private static final String HREF = "href";
    private static final String BASE_64 = "base64,";

    private final ElementsList attachments = new ElementsList(ATTACHMENTS_XPATH, "attachments");
    private final ElementsList imgAttachments = new ElementsList(ATTACHMENTS_XPATH + "//img", "imgAttachments");
    private final Label startTimeLabel = new Label(START_TIME_LABEL_XPATH, "startTimeLabel");
    private final Label endTimeLabel = new Label(END_TIME_LABEL_XPATH, "endTimeLabel");
    private final Label projectNameLabel = new Label(PROJECT_NAME_LABEL_XPATH, "projectNameLabel");
    private final Label methodNameLabel = new Label(METHOD_NAME_LABEL_XPATH, "methodNameLabel");
    private final Label statusLabel = new Label(STATUS_LABEL_XPATH, "statusLabel");
    private final Label environmentLabel = new Label(ENVIRONMENT_LABEL_XPATH, "environmentLabel");


    public TestPage() {
        super(ATTACHMENTS_XPATH);
    }

    public String getPageLogText() {
        attachments.findElements();
        //Индекс 0, т.к. нам нужен 1 элемент списка
        return attachments.getElement(0).getText();
    }

    public String getPageImgHref() {
        imgAttachments.findElements();
        //Индекс 0, т.к. нам нужен 1 элемент списка
        return imgAttachments.getElement(0).getAttribute(HREF);
    }

    public Label getStartTimeLabel() {
        return startTimeLabel;
    }

    public Label getEndTimeLabel() {
        return endTimeLabel;
    }

    public Label getProjectNameLabel() {
        return projectNameLabel;
    }

    public Label getMethodNameLabel() {
        return methodNameLabel;
    }

    public Label getStatusLabel() {
        return statusLabel;
    }

    public Label getEnvironment() {
        return environmentLabel;
    }

    public String getPicture() {
        imgAttachments.findElements();
        String data = imgAttachments.getElement(0).getAttribute("src");
        System.out.println("Element: " + data);
        data = data.substring(data.indexOf(BASE_64) + BASE_64.length());
        return data;
    }

}
