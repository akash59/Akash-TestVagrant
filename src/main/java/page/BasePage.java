package page;

import core.controller.Controller;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class BasePage {

    protected final WebDriver driver;
    protected final Controller controller;
    protected final WebDriverWait wait;
    private static final Logger LOG = LoggerFactory.getLogger(BasePage.class);

    public BasePage(Controller controller) {
        LOG.info("Instantiating Web Page....");
        this.controller = controller;
        this.driver = controller.getDriver();
        this.wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    protected abstract boolean isLoaded();
}
