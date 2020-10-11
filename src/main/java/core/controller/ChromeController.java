package core.controller;

import apiEngine.EndPoint;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChromeController implements Controller {

    private ThreadLocal<WebDriver> driver;
    private static final Logger LOG = LoggerFactory.getLogger(ChromeController.class);

    @Override
    public WebDriver getDriver() {
        if(null == driver) {
            setupController();
        }
        return driver.get();
    }

    @Override
    public void setupController() {
        LOG.info("Setting up Chrome browser");
        WebDriverManager.chromedriver().setup();
        this.driver = new ThreadLocal<>();
        driver.set(new ChromeDriver());
    }

    @Override
    public void setDriver(ThreadLocal<WebDriver> driver) {
        this.driver = driver;
    }
}
