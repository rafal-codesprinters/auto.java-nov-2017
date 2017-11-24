package codesprinters.autotest.base.pages;

import codesprinters.autotest.base.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WPPostsPanel extends PageObject {

    private By postSearchInput = By.id("post-search-input");
    private By postSearchSubmit = By.id("search-submit");

    private By postsCountAll = By.xpath("//li[@class='all']/a/span[@class='count']");
    private By postsCountPublished = By.xpath("//li[@class='publish']/a/span[@class='count']");
    private By postsCountDrafts = By.xpath("//li[@class='draft']/a/span[@class='count']");

    private By getPostBySubject(String subject) {
        return By.xpath("//a[@class='row-title' and text()='" + subject + "']");
    }

    public WPPostsPanel(WebDriver browser) {
        super(browser);
    }

    public void searchForPostBySubject(String subject) {
        waitForElementToBeClickable(postSearchSubmit);
        browser.findElement(postSearchInput).sendKeys(subject);
        browser.findElement(postSearchSubmit).click();
    }

    public int howManyPostsBySubject(String subject) {
        return browser.findElements(getPostBySubject(subject)).size();
    }

    public int getPostsCount() {
        return getValueFromBrackets(postsCountAll);
    }

    public int getPublishedPostsCount() {
        return getValueFromBrackets(postsCountPublished);
    }

    public int getDraftPostsCount() {
        return getValueFromBrackets(postsCountDrafts);
    }

}
