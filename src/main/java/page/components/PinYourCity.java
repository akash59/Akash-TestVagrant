package page.components;

import core.controller.Controller;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import page.BasePage;
import utils.PageActions;

public class PinYourCity extends BasePage {

    private final PageActions pageActions;
    private String city_loc = "//*[@id='%s']";

    @FindBy(how = How.ID, using = "searchBox")
    private WebElement search_box;

    private WebElement cityToSelect;


    public PinYourCity(Controller controller) {
        super(controller);
        pageActions = new PageActions(controller);
    }

    public void pinCity(String city) {
        cityToSelect = pageActions.getWebElement(By.xpath(String.format(city_loc, city)), 10);
        pageActions.clearText(search_box);
        pageActions.type(search_box, city);
        pageActions.enableCheckBox(cityToSelect);
    }

    @Override
    public boolean isLoaded() {
        return this.wait.until(d -> this.search_box.isDisplayed());
    }
}
