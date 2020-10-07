package page.common;

import core.controller.Controller;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import page.BasePage;
import utils.PageActions;

public class NotificationWrap extends BasePage {

    private final PageActions pageActions;

    @FindBy(how = How.CLASS_NAME, using = "noti_wrap")
    public WebElement notification_popover;

    @FindBy(how = How.LINK_TEXT, using = "No Thanks")
    public WebElement no_thanks_button;

    public NotificationWrap(Controller controller) {
        super(controller);
        pageActions = new PageActions(controller);
    }

    @Override
    public boolean isLoaded() {
        return this.wait.until(d -> this.notification_popover.isDisplayed());
    }
}
