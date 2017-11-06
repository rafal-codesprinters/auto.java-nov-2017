package autoTestJavaStatics;

import autoTestJavaStatics.pageObjects.WpMainPage;
import autoTestJavaStatics.pageObjects.WpPostPage;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Kuba on 2017-05-30.
 */
public class CommentTest extends BaseTests{

    @Test
    public void ShouldAddCommentToThirdPost() {

        // GIVEN / ARRANGE
        String commentText = GenerateRandomText();
        String commentAuthorEmail = GenerateRandomEmail();
        String commentAuthorName = GenerateRandomName();

        //WHEN / ACT
        WpMainPage.Open(driver);
        WpMainPage.DisplayPost(3, driver);
        WpPostPage.AddComment(commentText, commentAuthorEmail, commentAuthorName, driver);

        //THEN / ASSERT
        Assert.assertTrue("Comment should be posted", WpPostPage.IsCommentPosted(commentText, commentAuthorName, driver));
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
        WpMainPage.Open(driver);
        WpMainPage.DisplayPost(3, driver);
        WpPostPage.AddComment(commentText, commentAuthorEmail, commentAuthorName, driver);
        WpPostPage.AddReplyToComment(commentText, commentAuthorName, replyText, replyAuthorEmail, replyAuthorName, driver);

        //THEN / ASSERT
        Assert.assertTrue("Reply should be posted", WpPostPage.IsReplyPosted(commentText, commentAuthorName, replyText, replyAuthorName, driver));

    }
}
