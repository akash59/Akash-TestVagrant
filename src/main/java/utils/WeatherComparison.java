package utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import model.Weather;
import java.util.Comparator;

@RequiredArgsConstructor
public class WeatherComparison implements Comparator<Weather> {
    @Getter
    private final int variance_threshold;

    @Override
    public int compare(Weather o1, Weather o2) {
        System.out.println("UI "+o1.toString());
        System.out.println("API - "+o2.toString());
        System.out.println("Running comparison with threshold of " +variance_threshold);
        if(Math.abs(o1.getWeather_celcius() - o2.getWeather_celcius()) <= variance_threshold) {
            return 0;
        } else {
            throw new RuntimeException("Weather details did not match, as they are outside the variance threshold");
        }

    }
}