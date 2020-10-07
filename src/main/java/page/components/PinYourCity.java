package page.components;

import core.controller.Controller;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import page.BasePage;
import utils.PageActions;

public class PinYourCity extends BasePage {

    private final PageActions pageActions;

    @FindBy(how = How.ID, using = "searchBox")
    private WebElement search_box;

    @FindBy(how = How.ID, using = "New Delhi")
    private WebElement new_Delhi;


    public PinYourCity(Controller controller) {
        super(controller);
        pageActions = new PageActions(controller);
    }

    public void pinCity(String city) {
        pageActions.clearText(search_box);
        pageActions.type(search_box, city);
        pageActions.enableCheckBox(new_Delhi);
    }

    @Override
    public boolean isLoaded() {
        return this.wait.until(d -> this.search_box.isDisplayed());
    }
}
