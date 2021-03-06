package page.components;

import core.controller.Controller;
import model.Weather;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.PageActions;

public class WeatherDetail {

    private final PageActions pageActions;

    private static final String CELCIUS_TEMP = ".//div[@title='%s']//span[@class='tempRedText']";
    private static final String FAHRENHEIT_TEMP = ".//div[@title='%s']//span[@class='tempWhiteText']";
    private static final Logger LOG = LoggerFactory.getLogger(WeatherDetail.class);

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

    public Weather getWeather(String city) {
        LOG.info("getting weather details for city {} ", city);
        WebElement temp_celcius = getTemp_celcius(city);
        WebElement temp_fahrenheit = getTemp_fahrenheit(city);
        int temp_c = Integer.parseInt(temp_celcius.getText().replaceAll("[^0-9]", ""));
        LOG.info("Temperature in celcius is {}", temp_c);
        int temp_f = Integer.parseInt(temp_fahrenheit.getText().replaceAll("[^0-9]", ""));
        LOG.info("Temperature in fahrenheit is {}", temp_f);
        return new Weather(city, temp_c, temp_f);
    }
}
