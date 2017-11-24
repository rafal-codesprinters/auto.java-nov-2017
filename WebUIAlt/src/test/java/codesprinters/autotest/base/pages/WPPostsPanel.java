package codesprinters.autotest.base.pages;

import codesprinters.autotest.base.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WPPostsPanel extends PageObject {

    private @FindBy(id = "post-search-input") WebElement postSearchInput;
    private @FindBy(id = "search-submit") WebElement  postSearchSubmit;

    private @FindBy(xpath = "//li[@class='all']/a/span[@class='count']") WebElement postsCountAll;
    private @FindBy(xpath =  "//li[@class='publish']/a/span[@class='count']") WebElement postsCountPublished;
    private @FindBy(xpath = "//li[@class='draft']/a/span[@class='count']") WebElement postsCountDrafts;

    private By getPostBySubject(String subject) {
        return By.xpath("//a[@class='row-title' and text()='" + subject + "']");
    }

    public WPPostsPanel(WebDriver browser) {
        super(browser);
        PageFactory.initElements(browser, this);
    }

    public void searchForPostBySubject(String subject) {
        waitForElementToBeClickable(postSearchSubmit);
        postSearchInput.sendKeys(subject);
        postSearchSubmit.click();
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
