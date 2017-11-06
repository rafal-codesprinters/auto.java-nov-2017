package autoTestJavaStatics.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Kuba on 2017-05-30.
 */
public abstract class WpPostPage extends WpPage{

    private static final By LOCATOR_COMMENT_BOX = By.id("comment");
    private static final By LOCATOR_COMMENT_EMAIL_BOX_HINT = By.cssSelector(".comment-form-email > label");
    private static final By LOCATOR_COMMENT_EMAIL_BOX = By.id("email");
    private static final By LOCATOR_COMMENT_NAME_BOX_HINT = By.cssSelector(".comment-form-author > label");
    private static final By LOCATOR_COMMENT_NAME_BOX = By.id("author");
    private static final By LOCATOR_COMMENT_POST_BUTTON = By.id("comment-submit");
    private static final By LOCATOR_COMMENT_REPLY_LINK = By.className("comment-reply-link");

    private static final By LOCATOR_REPLY_BOX = By.id("comment");
    private static final By LOCATOR_REPLY_EMAIL_BOX = By.id("email");
    private static final By LOCATOR_REPLY_NAME_BOX = By.id("author");
    private static final By LOCATOR_REPLY_POST_BUTTON = By.id("comment-submit");

    public static void AddComment(String commentText, String commentAuthorEmail, String commentAuthorName, WebDriver driver) {
        WaitUntilElementIsClickable(LOCATOR_COMMENT_BOX, driver);
        WebElement commentBox = driver.findElement(LOCATOR_COMMENT_BOX);
        commentBox.click();
        commentBox.clear();
        commentBox.sendKeys(commentText);

        WaitUntilElementIsClickable(LOCATOR_COMMENT_EMAIL_BOX_HINT, driver);
        WebElement emailBoxHint = driver.findElement(LOCATOR_COMMENT_EMAIL_BOX_HINT);
        emailBoxHint.click();
        WaitUntilElementIsHidden(LOCATOR_COMMENT_EMAIL_BOX_HINT, driver);
        WebElement emailBox = driver.findElement(LOCATOR_COMMENT_EMAIL_BOX);
        emailBox.click();
        emailBox.clear();
        emailBox.sendKeys(commentAuthorEmail);

        WaitUntilElementIsClickable(LOCATOR_COMMENT_NAME_BOX_HINT, driver);
        WebElement nameBoxHint = driver.findElement(LOCATOR_COMMENT_NAME_BOX_HINT);
        nameBoxHint.click();
        WaitUntilElementIsHidden(LOCATOR_COMMENT_NAME_BOX_HINT, driver);
        WebElement nameBox = driver.findElement(LOCATOR_COMMENT_NAME_BOX);
        nameBox.click();
        nameBox.clear();
        nameBox.sendKeys(commentAuthorName);

        WaitUntilElementIsClickable(LOCATOR_COMMENT_POST_BUTTON, driver);
        WebElement postButton = driver.findElement(LOCATOR_COMMENT_POST_BUTTON);
        postButton.click();
        WaitUntilElementIsHidden(LOCATOR_COMMENT_POST_BUTTON, driver);
    }

    public static boolean IsCommentPosted(String commentText, String commentAuthorName, WebDriver driver) {
        By commentLocator = GetPostedCommentLocator(commentText, commentAuthorName);
        try {
            driver.findElement(commentLocator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    private static By GetPostedCommentLocator(String commentText, String commentAuthorName) {
        return By.xpath("//li/article[.//cite[text()='" + commentAuthorName + "'] and .//p[text()='" + commentText + "']]");
    }

    public static void AddReplyToComment(String commentText, String commentAuthorName, String replyText, String replyAuthorEmail, String replyAuthorName, WebDriver driver) {
        By commentLocator = GetPostedCommentLocator(commentText, commentAuthorName);
        WebElement postedComment = driver.findElement(commentLocator);
        WebElement replyLink = postedComment.findElement(LOCATOR_COMMENT_REPLY_LINK);
        replyLink.click();

        WaitUntilElementIsClickable(LOCATOR_REPLY_BOX, driver);
        WebElement replyBox = driver.findElement(LOCATOR_REPLY_BOX);
        replyBox.click();
        replyBox.clear();
        replyBox.sendKeys(replyText);

        WebElement emailBox = driver.findElement(LOCATOR_REPLY_EMAIL_BOX);
        emailBox.click();
        emailBox.clear();
        emailBox.sendKeys(replyAuthorEmail);

        WebElement nameBox = driver.findElement(LOCATOR_REPLY_NAME_BOX);
        nameBox.click();
        nameBox.clear();
        nameBox.sendKeys(replyAuthorName);

        WaitUntilElementIsClickable(LOCATOR_REPLY_POST_BUTTON, driver);
        WebElement postButton = driver.findElement(LOCATOR_REPLY_POST_BUTTON);
        postButton.click();
        WaitUntilElementIsHidden(LOCATOR_REPLY_POST_BUTTON, driver);
    }

    private static By GetPostedReplyLocator(String replyText, String replyAuthorName) {
        return By.xpath("//article[.//cite[text()='" + replyAuthorName + "'] and .//p[text()='" + replyText + "']]");
    }

    public static boolean IsReplyPosted(String commentText, String commentAuthorName, String replyText, String replyAuthorName, WebDriver driver) {
        By commentLocator = GetPostedCommentLocator(commentText, commentAuthorName);
        WebElement postedComment = driver.findElement(commentLocator);
        By replyLocator = GetPostedReplyLocator(replyText, replyAuthorName);
        try {
            postedComment.findElement(replyLocator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

}
