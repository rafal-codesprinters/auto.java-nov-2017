package autoTestJavaFullObjects.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Rafal on 2017-05-31.
 */
public abstract class WpPage {

    protected WebDriver driver;
    public static final String URL_MAIN_PAGE = "http://autotestjava.wordpress.com";
    protected static final By LOCATOR_FOOTER = By.tagName("footer");

    public WpPage(WebDriver driver) {
        this.driver = driver;
    }

    protected static void WaitUntilElementIsClickable(By byLocator, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(byLocator));
    }

    protected static void WaitUntilElementIsVisible(By byLocator, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(byLocator));
    }

    protected static void WaitUntilElementIsHidden(By byLocator, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
    }

    protected static void WaitUntilFooterIsDisplayed(WebDriver driver) {
        WaitUntilElementIsVisible(WpPage.LOCATOR_FOOTER, driver);
    }
}
