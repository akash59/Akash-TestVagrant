package core.controller;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirefoxController implements Controller {

    private ThreadLocal<WebDriver> driver;
    private static final Logger LOG = LoggerFactory.getLogger(FirefoxController.class);

    @Override
    public WebDriver getDriver() {
        if (null == driver) {
            setupController();
        }
        return driver.get();
    }

    @Override
    public void setDriver(ThreadLocal<WebDriver> driver) {
        this.driver = driver;
    }

    @Override
    public void setupController() {
        LOG.info("Setting up firefox browser");
        WebDriverManager.firefoxdriver().setup();
        this.driver = new ThreadLocal<>();
        driver.set(new FirefoxDriver());

    }
}
