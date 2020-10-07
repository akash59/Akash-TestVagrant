package stepdefs;

import core.controller.Controller;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.RestUtil;

import java.util.HashMap;
import java.util.Map;

public class TestSetup extends BaseTest {

    private WebDriver driver;
    private int counter = 1;
    private final Controller controller;
    private static final String APP_URL = "https://ndtv.com";

    public TestSetup(Controller controller) {
        this.controller = controller;
    }


    @Before
    public void init(Scenario scenario) {
        BaseTest.scenario.set(scenario);
        long id = Thread.currentThread().getId();
        scenario.write("Before scenario. Thread id is: " + id);
        scenario.write("launching browser...");
        driver = controller.getDriver();
        driver.manage().window().maximize();
        scenario.write("setting up base uri for API tests...");
        RestUtil.setBaseURI("https://openweathermap.org");
        scenario.write("Opening Application under Test...");
    }


    @After(order = 1)
    public void embedScreenshot(Scenario scenario)
    {
        if (scenario.isFailed() && driver != null)
        {
            scenario.write("taking screenshot for failed scenario");
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png", "failure" + "_" + counter++ + ".png");
        }
    }

    @After(order = 0)
    public void tear_down(Scenario scenario) {
        long id = Thread.currentThread().getId();
        scenario.write("After scenario. Thread id is: " + id);
        scenario.write("shutting down browser");
        controller.teardownController();
        scenario.write("resetting BASE URI");
        RestUtil.resetBaseURI();
    }

}
