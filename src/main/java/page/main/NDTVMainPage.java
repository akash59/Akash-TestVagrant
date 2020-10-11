package page.main;

import core.controller.Controller;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page.common.NotificationWrap;
import page.common.PageHeader;
import page.components.WeatherDetail;
import utils.PageActions;

@Getter
public class NDTVMainPage {

    private final PageHeader pageHeader;
    private final PageActions pageActions;
    private final NotificationWrap notificationWrap;
    private static final Logger LOG = LoggerFactory.getLogger(NDTVMainPage.class);

    public NDTVMainPage(Controller controller) {
        this.pageHeader = new PageHeader(controller);
        this.pageActions = new PageActions(controller);
        this.notificationWrap = new NotificationWrap(controller);
    }

    public void goToLandingPage(String url) {
        //read from a prop file
        LOG.info("Opening application under test url {}", url);
        pageActions.navigateTo(url);
        if(notificationWrap.isLoaded()) {
            notificationWrap.ignoreNotification();
        }
    }
}
