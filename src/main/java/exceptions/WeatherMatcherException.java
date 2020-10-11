package exceptions;

public class WeatherMatcherException extends Exception {
    public WeatherMatcherException(String errorMessage) {
        super(errorMessage);
    }
}
