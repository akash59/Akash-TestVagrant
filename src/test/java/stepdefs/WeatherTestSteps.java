package stepdefs;


import apiEngine.EndPoint;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import page.main.NDTVMainPage;
import page.main.NDTVWeatherReportPage;
import utils.WeatherComparison;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@RequiredArgsConstructor
public class WeatherTestSteps extends BaseTest
{

    private static final Logger LOG = LoggerFactory.getLogger(WeatherTestSteps.class);
    private final NDTVMainPage ndtvMainPage;
    private final NDTVWeatherReportPage ndtvWeatherReportPage;
    private Weather ndtvWeather;
    private Weather apiWeather;

    @When("user visits ndtv website")
    public void openNdtvWebsite() {
        scenario.get().write("Opening NDTV Home page");
        ndtvMainPage.goToLandingPage(APP_URL);
    }

    @And("opens the weather section")
    public void openWeatherPage() {
        scenario.get().write("Opening Weather Report page");
        Assert.assertTrue(ndtvMainPage.getPageHeader().isLoaded());
        ndtvMainPage.getPageHeader().navigateToWeatherPage();
    }

    @And("selects the city {string} from Pin your city section")
    public void pinYourCity(String city) {
        scenario.get().write("Select city as "+city);
        Assert.assertTrue(ndtvWeatherReportPage.getPinYourCity().isLoaded());
        ndtvWeatherReportPage.getPinYourCity().pinCity(city);
    }

    @Then("pinned city {string} shall be shown on the map with temperature information")
    public void verifyPinnedCityTemperatureInfo(String city) {
        try {
            ndtvWeather = ndtvWeatherReportPage.getWeatherDetail().getWeather(city);
            scenario.get().write("Weather for City "+ ndtvWeather.getCity() +": ");
            scenario.get().write("In Celcius "+ ndtvWeather.getWeather_celcius());
        } catch (Exception e) {
            scenario.get().write("Temp details for "+city+" could not be fetched ");
            LOG.error("Temp details for city {} could not be fetched", city);
            throw new RuntimeException("Error fetching Temp details from the website");
        }
    }


    @Then("get the weather details from the api")
    public void getTheWeatherDetailsFromTheApi(Map<String, String> params) {
        EndPoint endPoint = new EndPoint(API_URL);
        final Response response = endPoint.getWeatherFromAPIWithParams(params);
        scenario.get().write(response.asString());
        String city = response.path("name").toString();
        int temp_c = (int) Float.parseFloat(response.path("main.temp").toString());
        scenario.get().write("Temperature retrieved from API as actual " +temp_c);
        apiWeather = new Weather(city, temp_c, 0);
    }

    @Then("temperature difference shall comply with the variance threshold {int}")
    public void VerifyTemperatureDifference(int limit) {
        WeatherComparison weatherComparison = new WeatherComparison(limit);
        AtomicInteger compare = new AtomicInteger(weatherComparison.compare(ndtvWeather, apiWeather));
        assertThatCode(() -> compare.set(weatherComparison.compare(ndtvWeather, apiWeather))).doesNotThrowAnyException();
        assertThat(compare).as("Weather details match").hasValue(0);
    }
}