package utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;

@RequiredArgsConstructor
@Getter
public class WeatherComparison implements Comparator<Weather> {

    private final int variance_threshold;
    private static final Logger LOG = LoggerFactory.getLogger(WeatherComparison.class);

    @Override
    public int compare(Weather o1, Weather o2) {
        LOG.info("Weather details from UI - {} ", o1.toString());
        LOG.info("Weather details from API - {} ", o2.toString());
        LOG.info("Running comparison with threshold of " +variance_threshold);
        if(Math.abs(o1.getWeather_celcius() - o2.getWeather_celcius()) <= variance_threshold) {
            LOG.info("Weather details match successful with the variance of {}", variance_threshold);
            return 0;
        } else {
            throw new RuntimeException("Weather details did not match, as they are outside the variance threshold");
        }

    }
}