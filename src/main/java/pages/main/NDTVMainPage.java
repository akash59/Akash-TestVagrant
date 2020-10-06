package pages.main;

import core.controller.Controller;
import lombok.Getter;
import page_utils.PageActions;
import pages.common.PageHeader;

@Getter
public class NDTVMainPage {

    private final PageHeader pageHeader;
    private final PageActions pageActions;

    public NDTVMainPage(Controller controller) {
        this.pageHeader = new PageHeader(controller);
        this.pageActions = new PageActions(controller);
    }

    public void goToLandingPage() {
        //read from a prop file
        pageActions.navigateTo("https://ndtv.com");
    }
}
