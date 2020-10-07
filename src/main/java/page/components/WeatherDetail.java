package page.components;

import core.controller.Controller;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.PageActions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherDetail {

    private final PageActions pageActions;

    private static final String CELCIUS_TEMP = ".//div[@title='%s']//span[@class='tempRedText']";
    private static final String FAHRENHEIT_TEMP = ".//div[@title='%s']//span[@class='tempWhiteText']";

    public WeatherDetail(Controller controller) {
        pageActions = new PageActions(controller);
    }

    public WebElement getTemp_celcius(String city) {
        String CELCIUS_LOC = String.format(CELCIUS_TEMP, city);
        return pageActions.getWebElement(By.xpath(CELCIUS_LOC), 10);
    }

    public WebElement getTemp_fahrenheit(String city) {
        String FAHRENHEIT_LOC = String.format(FAHRENHEIT_TEMP, city);
        return pageActions.getWebElement(By.xpath(FAHRENHEIT_LOC), 10);
    }

    public Map<String, List<String>> getWeather(String city) {
        Map<String, List<String>> weatherDetails = new HashMap<>();
        WebElement temp_celcius = getTemp_celcius(city);
        WebElement temp_fahrenheit = getTemp_fahrenheit(city);
        weatherDetails.put(city, List.of(temp_celcius.getText(), temp_fahrenheit.getText()));
        return weatherDetails;
    }
}
