package autoTestJavaStatics.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Kuba on 2017-05-30.
 */
public abstract class WpMainPage extends WpPage {

    private static final By POST_LINK_LOCATOR = By.className("entry-header");

    public static void Open(WebDriver driver) {
        driver.get(WpPage.URL_MAIN_PAGE);
        WpPage.WaitUntilFooterIsDisplayed(driver);
    }

    public static void DisplayPost(int postNumber, WebDriver driver) {
        By postLocator = By.xpath("//article[" + postNumber + "]");
        WebElement post = driver.findElement(postLocator);
        WebElement postLink = post.findElement(POST_LINK_LOCATOR);
        postLink.click();
        WaitUntilFooterIsDisplayed(driver);
    }
}
