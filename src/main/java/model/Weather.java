package model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class Weather {

    private final String city;
    private final int weather_celcius;
    private final int weather_fahrenheit;

}
