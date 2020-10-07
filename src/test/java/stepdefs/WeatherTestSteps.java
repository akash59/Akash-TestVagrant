package stepdefs;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.testng.Assert;
import page.main.NDTVMainPage;
import page.main.NDTVWeatherReportPage;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class WeatherTestSteps extends BaseTest
{

    private final NDTVMainPage ndtvMainPage;
    private final NDTVWeatherReportPage ndtvWeatherReportPage;

    // No need to define the constructor now as we are using lombak -> @AllArgsConstructor during execution

    @When("user visits ndtv website")
    public void openNdtvWebsite() {
        ndtvMainPage.goToLandingPage();
    }

    @And("opens the weather section")
    public void openWeatherPage() {
        Assert.assertTrue(ndtvMainPage.getPageHeader().isLoaded());
        ndtvMainPage.getPageHeader().navigateToWeatherPage();
    }

    @Then("selects the city {string} from Pin your city section")
    public void pinYourCity(String city) {
        Assert.assertTrue(ndtvWeatherReportPage.getPinYourCity().isLoaded());
        ndtvWeatherReportPage.getPinYourCity().pinCity(city);
    }

    @Then("pinned city {string} shall be shown on the map with temperature information")
    public void verifyPinnedCityTemperatureInfo(String city) {
        final Map<String, List<String>> weather = ndtvWeatherReportPage.getWeatherDetail().getWeather(city);
        weather.forEach((k,v) -> System.out.println("Weather for City "+ k + " is " +v));
    }




}