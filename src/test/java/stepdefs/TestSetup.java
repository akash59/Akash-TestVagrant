package stepdefs;

import core.controller.Controller;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WeatherComparison;

public class TestSetup extends BaseTest {

    private WebDriver driver;
    private int counter = 1;
    private final Controller controller;
    private static final Logger LOG = LoggerFactory.getLogger(TestSetup.class);

    public TestSetup(Controller controller) {
        this.controller = controller;
    }


    @Before
    public void init(Scenario scenario) {
        BaseTest.scenario.set(scenario);
        LOG.info("launching browser...");
        driver = controller.getDriver();
        driver.manage().window().maximize();
    }


    @After(order = 1)
    public void embedScreenshot(Scenario scenario)
    {
        if (scenario.isFailed() && driver != null)
        {
            scenario.write("taking screenshot for failed scenario");
            LOG.info("taking screenshot for failed scenario");
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png", "failure" + "_" + counter++ + ".png");
        }
    }

    @After(order = 0)
    public void tear_down(Scenario scenario) {
        scenario.write("shutting down browser");
        LOG.info("shutting down browser");
        controller.teardownController();
    }

}
