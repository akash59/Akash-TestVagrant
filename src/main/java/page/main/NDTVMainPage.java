package page.main;

import core.controller.Controller;
import lombok.Getter;
import page.common.NotificationWrap;
import page.common.PageHeader;
import utils.PageActions;

@Getter
public class NDTVMainPage {

    private final PageHeader pageHeader;
    private final PageActions pageActions;
    private final NotificationWrap notificationWrap;

    public NDTVMainPage(Controller controller) {
        this.pageHeader = new PageHeader(controller);
        this.pageActions = new PageActions(controller);
        this.notificationWrap = new NotificationWrap(controller);
    }

    public void goToLandingPage(String url) {
        //read from a prop file
        pageActions.navigateTo(url);
        if(notificationWrap.isLoaded()) {
            pageActions.click(notificationWrap.no_thanks_button);
        }
    }
}
