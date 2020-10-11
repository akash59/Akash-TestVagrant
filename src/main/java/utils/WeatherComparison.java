package utils;

import exceptions.WeatherMatcherException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;

@RequiredArgsConstructor
@Getter
public class WeatherComparison implements Comparator<Weather> {

    private final int VARIANCE;
    private static final Logger LOG = LoggerFactory.getLogger(WeatherComparison.class);

    @SneakyThrows
    @Override
    public int compare(Weather o1, Weather o2) {
        LOG.info("Weather details from UI - {} ", o1.toString());
        LOG.info("Weather details from API - {} ", o2.toString());
        LOG.info("Running comparison with threshold of " +VARIANCE);
        int diff = Math.abs(o1.getWeather_celcius() - o2.getWeather_celcius());
        if(diff <= VARIANCE) {
            LOG.info("Weather details match successful with the variance of {}", VARIANCE);
            return 0;
        } else {
            LOG.info("Weather details did not match - outside the variance threshold by {} degrees", diff);
            throw new WeatherMatcherException("Weather details did not match - outside the variance threshold by "+diff+ " degrees");
        }
    }
}