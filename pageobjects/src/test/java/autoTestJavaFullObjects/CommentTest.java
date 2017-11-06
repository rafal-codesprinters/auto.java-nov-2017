package autoTestJavaFullObjects;

import autoTestJavaFullObjects.pageObjects.WpMainPage;
import autoTestJavaFullObjects.pageObjects.WpPostPage;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rafal on 2017-05-31.
 */
public class CommentTest extends BaseTests {

    @Test
    public void ShouldAddCommentToThirdPost() {

        // GIVEN / ARRANGE
        String commentText = GenerateRandomText();
        String commentAuthorEmail = GenerateRandomEmail();
        String commentAuthorName = GenerateRandomName();

        //WHEN / ACT
        WpMainPage mainPage = new WpMainPage(this.driver);
        mainPage.Open();
        WpPostPage postPage = mainPage.DisplayPost(3);
        postPage.AddComment(commentText, commentAuthorEmail, commentAuthorName);

        //THEN / ASSERT
        Assert.assertTrue("Comment should be posted", postPage.IsCommentPosted(commentText, commentAuthorName));

    }

    @Test
    public void ShouldAddReplyToComment() {

        // GIVEN / ARRANGE
        String commentText = GenerateRandomText();
        String commentAuthorEmail = GenerateRandomEmail();
        String commentAuthorName = GenerateRandomName();

        String replyText = GenerateRandomText();
        String replyAuthorEmail = GenerateRandomEmail();
        String replyAuthorName = GenerateRandomName();

        //WHEN / ACT
        WpMainPage mainPage = new WpMainPage(this.driver);
        mainPage.Open();
        WpPostPage postPage = mainPage.DisplayPost(1);
        postPage.AddComment(commentText, commentAuthorEmail, commentAuthorName);
        postPage.AddReplyToComment(commentText, commentAuthorName, replyText, replyAuthorEmail, replyAuthorName);

        //THEN / ASSERT
        Assert.assertTrue("Reply should be posted", postPage.IsReplyPosted(commentText, commentAuthorName, replyText, replyAuthorName));

    }
}
