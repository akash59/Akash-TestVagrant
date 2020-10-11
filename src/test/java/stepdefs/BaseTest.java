package stepdefs;

import io.cucumber.java.Scenario;

public class BaseTest {

    protected static final String APP_URL = "https://www.ndtv.com";
    protected static final String API_URL = "http://api.openweathermap.org";

    protected static ThreadLocal<Scenario> scenario = new ThreadLocal<>();

}
