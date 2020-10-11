package page.components;

import core.controller.Controller;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page.BasePage;
import utils.PageActions;

public class PinYourCity extends BasePage {

    private final PageActions pageActions;
    private static final Logger LOG = LoggerFactory.getLogger(PinYourCity.class);
    private String city_loc = "//*[@id='%s']";

    @FindBy(how = How.ID, using = "searchBox")
    private WebElement search_box;

    private WebElement cityToSelect;


    public PinYourCity(Controller controller) {
        super(controller);
        pageActions = new PageActions(controller);
    }

    public void pinCity(String city) {
        LOG.info("Pinning city {} to get weather information", city);
        cityToSelect = pageActions.getWebElement(By.xpath(String.format(city_loc, city)), 10);
        pageActions.clearText(search_box);
        pageActions.type(search_box, city);
        pageActions.enableCheckBox(cityToSelect);
    }

    @Override
    public boolean isLoaded() {
        LOG.info("Wait for Pin your city section to load");
        return this.wait.until(d -> this.search_box.isDisplayed());
    }
}
