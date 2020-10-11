package utils;

import core.controller.Controller;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page.components.WeatherDetail;

import java.util.Objects;

public class PageActions {

    protected WebDriverWait wait;
    protected WebDriver driver;
    private static final Logger LOG = LoggerFactory.getLogger(PageActions.class);

    public PageActions(Controller controller) {
        this.driver = controller.getDriver();
        this.wait = getWait(30);
    }

    public WebDriverWait getWait(long timeOut) {
        if(null != wait) {
            return wait;
        }
        return new WebDriverWait(driver, timeOut);
    }


    public void setWait(WebDriverWait wait) {
        this.wait = wait;
    }

    public void type(By loc, CharSequence text) {
        driver.findElement(loc).sendKeys(text);
    }

    public void type(WebElement element, CharSequence text) {
        LOG.info("Type into web element - {}", text);
        waitForVisibilityOfElement(element, 2000);
        element.sendKeys(text);
    }

    public void click(By loc) {
        wait.until(ExpectedConditions.elementToBeClickable(loc));
        driver.findElement(loc).click();
    }

    public void click(WebElement element) {
        this.wait
                .ignoring(StaleElementReferenceException.class)
                .until(d -> ExpectedConditions.elementToBeClickable(element));
        try {
            LOG.info("Clicking web element");
            element.click();
        }
        catch (StaleElementReferenceException se) {
            System.out.println("Stale element exception "+se.getMessage());
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
        }
    }

    public boolean retryingFindClick(By by) {
        boolean result = false;
        int attempts = 0;
        while(attempts < 2) {
            try {
                driver.findElement(by).click();
                result = true;
                break;
            } catch(StaleElementReferenceException e) {
            }
            attempts++;
        }
        return result;
    }

    public void pressEnterKey(WebElement element) {
        type(element, Keys.ENTER);
    }

    public void openPage(String url){
        driver.navigate().to(url);
    }

    public WebElement getWebElement(By loc, long timeout) {
        return getWait(timeout).until(driver -> driver.findElement(loc));
    }

    public void waitForVisibilityOfElement(By locator, long timeOut)
    {
        getWait(timeOut).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForVisibilityOfElement(WebElement element, long timeOut)
    {
        wait = getWait(timeOut);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected Boolean isEnabled(By locator, long timeOutInSeconds)
    {
        waitForVisibilityOfElement(locator, timeOutInSeconds);
        return driver.findElement(locator).isEnabled();
    }


    public void clearText(WebElement searchBox) {
        searchBox.clear();
    }

    public void navigateTo(String s) {
        driver.navigate().to(s);
    }

    public boolean waitForJSandJQueryToLoad() {
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return ((Long)((JavascriptExecutor) Objects.requireNonNull(driver))
                        .executeScript("return jQuery.active") == 0);
            }
            catch (Exception e) {
                // no jQuery present
                return true;
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) Objects.requireNonNull(driver))
                .executeScript("return document.readyState")
                .toString().equals("complete");

        return this.wait.until(jQueryLoad) && this.wait.until(jsLoad);
    }

    public void enableCheckBox(WebElement element) {
        if (null == element.getAttribute("checked") || !"true".equals(element.getAttribute("checked").trim())) {
            LOG.info("Enabling checkbox");
            click(element);
        } else {
            LOG.info("Checkbox is already clicked");
        }
    }

    public void disableCheckBox(WebElement element) {
        if ("false".equals(element.getAttribute("checked"))) {
            click(element);
            LOG.info("disabling checkbox");
        } else {
            LOG.info("Checkbox is already unchecked");
        }
    }

}
