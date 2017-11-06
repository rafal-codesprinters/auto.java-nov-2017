package autoTestJavaFullObjects.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Rafal on 2017-05-31.
 */
public class WpMainPage extends WpPage {

    private static final By LOCATOR_POST_HEADER_LINK = By.className("entry-header");

    public WpMainPage(WebDriver driver) {
        super(driver);
    }

    public void Open() {
        driver.get(WpPage.URL_MAIN_PAGE);
        WpPage.WaitUntilFooterIsDisplayed(driver);
    }

    public WpPostPage DisplayPost(int postNumber) {
        By postLocator = By.xpath("//article[" + postNumber + "]");
        WebElement post = driver.findElement(postLocator);
        WebElement postLink = post.findElement(LOCATOR_POST_HEADER_LINK);
        postLink.click();
        WaitUntilFooterIsDisplayed(driver);
        return new WpPostPage(driver);
    }
}
