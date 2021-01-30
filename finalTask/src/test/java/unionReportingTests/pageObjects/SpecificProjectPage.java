package unionReportingTests.pageObjects;

import apiUtils.JSONUtils;
import elements.Button;
import elementsUtils.ElementsList;
import logger.Logger;
import pages.BasePage;
import unionReportingTests.constants.Sign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpecificProjectPage extends BasePage {
    private static final Logger LOG = new Logger(SpecificProjectPage.class);

    private static final String NAME = "name";
    private static final String KS = "KS";

    private static final String LOCATOR = "//div[@id='graph']//canvas[@class='flot-base']";
    private static final String TEST_NAME_LABEL_XPATH = "//table[@class='table']//tr//td[1]";
    private static final String TEST_NAME_BUTTON_XPATH = "//table[@class='table']//tbody//tr//td//a[contains(text(),'name')]";
    private static final String HOME_BUTTON_XPATH = "//a[contains(text(), 'Home')]";
    private static final String DATES_LIST_XPATH = "//tr//td[4]";

    private final Button homeButton = new Button(HOME_BUTTON_XPATH, "homeButton");
    private final ElementsList datesList = new ElementsList(DATES_LIST_XPATH, "datesList");
    private final ElementsList testNames = new ElementsList(TEST_NAME_LABEL_XPATH, "testNames");
    private final Button firstTestButton = new Button(TEST_NAME_BUTTON_XPATH, "firstTestButton");

    public SpecificProjectPage() {
        super(LOCATOR);
    }

    public void goToHome() {
        homeButton.clickElement();
    }

    public boolean isDatesSorted() {
        LOG.info("Initialization of WebElements dates and String dates list");

        datesList.findElements();

        ArrayList<String> dates = (ArrayList<String>) datesList.getListOfElementsTexts();

        LOG.info("Sort dates array");
        ArrayList<String> sortedDates = (ArrayList<String>) datesList.getListOfElementsTexts();
        Collections.sort(sortedDates);
        Collections.reverse(sortedDates);

        LOG.info("Check that sorted array is equal to unsorted");
        return dates.equals(sortedDates);
    }

    public boolean isTestCorrect(String testData) {
        LOG.info("Initialization of correctsTestsAmount variable and tests array");
        int correctsTestsAmount = 0;
        List<String> list = null;
        if (testData.contains("{")) {
            list = JSONUtils.getJSONList(testData, NAME);
        } else {
            for (String el: testData.split(KS)) {
                list.add(KS + el.substring(0, el.indexOf(Sign.COMMA)));
            }
        }

        LOG.info("Initialization of WebElement tests list");
        testNames.findElements();
        List<String> testsList = testNames.getListOfElementsTexts();
        LOG.info("Check that tests array contains every test from testsList");

        for (String e : testsList) {
            for (String el : list) {
                if (el.contains(e)) {
                    correctsTestsAmount++;
                }
            }
        }
        LOG.info("Check that number of tests on the page is equal to correctsTestsAmount");
        //Проверка, что количество тестов на странице совпадает с количестов совпадающих тестов
        return testsList.size() == correctsTestsAmount;
    }

    public void clickOnFirstTest() {
        firstTestButton.clickElement();
    }

    public boolean isFirstTestDisplay() {
        return firstTestButton.isElementDisplayed();
    }

}
