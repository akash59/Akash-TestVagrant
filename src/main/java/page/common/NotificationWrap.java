package page.common;

import core.controller.Controller;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page.BasePage;
import utils.PageActions;

public class NotificationWrap extends BasePage {

    private final PageActions pageActions;
    private static final Logger LOG = LoggerFactory.getLogger(NotificationWrap.class);

    @FindBy(how = How.CLASS_NAME, using = "noti_wrap")
    private WebElement notification_popover;

    @FindBy(how = How.LINK_TEXT, using = "No Thanks")
    private WebElement no_thanks_button;

    public void ignoreNotification() {
        LOG.info("clicking no thanks button on the notification wrap");
        pageActions.click(no_thanks_button);
    }

    public NotificationWrap(Controller controller) {
        super(controller);
        pageActions = new PageActions(controller);
    }

    @Override
    public boolean isLoaded() {
        LOG.info("wait for the notification wrap to load");
        return this.wait.until(d -> this.notification_popover.isDisplayed());
    }
}
