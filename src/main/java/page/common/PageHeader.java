package page.common;

import core.controller.Controller;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page.BasePage;
import utils.PageActions;

public class PageHeader extends BasePage {

    private final PageActions pageActions;
    private static final Logger LOG = LoggerFactory.getLogger(PageHeader.class);

    @FindBy(how = How.ID, using = "header2")
    private WebElement nav_menu;

    @FindBy(how = How.ID, using = "h_sub_menu")
    private WebElement more_menu;

    @FindBy(how = How.LINK_TEXT, using = "WEATHER")
    private WebElement weatherLink;

    public PageHeader(Controller controller) {
        super(controller);
        pageActions = new PageActions(controller);
    }

    public void navigateToWeatherPage() {
        LOG.info("Navigating to weather page ...");
        pageActions.click(more_menu);
        pageActions.click(weatherLink);
    }

    public boolean isLoaded() {
        LOG.info("Wait for the page header to load ...");
        return this.wait.until(d -> this.nav_menu.isDisplayed());
    }

}
