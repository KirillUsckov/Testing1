import PageObjects.AviasalesMainPage;
import PageObjects.AviasalesSearchingPage;

public class FirstStep {

    public static void start(String departureCity, String destinationCity, boolean shouldCheckCheckbox,
                                            AviasalesMainPage mainPage, AviasalesSearchingPage searchingPage) {

        mainPage.enterCityDeparture(departureCity);
        mainPage.enterDestinationCity(destinationCity);

        mainPage.enterDepartureDate();
        mainPage.withoutReturnDate();

        if (shouldCheckCheckbox) {
            mainPage.withoutHotel();
        }
        mainPage.clickSearchingButton();

        searchingPage.waitingForOpen();

    }

}
