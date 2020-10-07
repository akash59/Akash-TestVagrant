package page.main;

import core.controller.Controller;
import lombok.Getter;
import page.components.PinYourCity;
import page.components.WeatherDetail;
import utils.PageActions;

import java.util.List;
import java.util.Map;

@Getter
public class NDTVWeatherReportPage {

    private final PageActions pageActions;
    private final PinYourCity pinYourCity;
    private final WeatherDetail weatherDetail;

    public NDTVWeatherReportPage(Controller controller) {
        this.pageActions = new PageActions(controller);
        this.pinYourCity = new PinYourCity(controller);
        this.weatherDetail = new WeatherDetail(controller);
    }

}
