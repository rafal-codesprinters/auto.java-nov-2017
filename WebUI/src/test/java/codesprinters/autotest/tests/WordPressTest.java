package codesprinters.autotest.tests;

import codesprinters.autotest.base.TestBase;
import codesprinters.autotest.base.pages.WPAdminMenu;
import codesprinters.autotest.base.pages.WPLoginPage;
import codesprinters.autotest.base.pages.WPPostsPanel;
import codesprinters.autotest.domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WordPressTest extends TestBase{

    @Before
    public void launchWP() {
        browser.get(getLoginURL());
    }

    @Test
    public void verifyIfExactlyOnePost() {
        final String POST_SUBJECT = "Buum!";
        final int EXPECTED_COUNT = 1;

        navigateToPostPage();

        WPPostsPanel posts = new WPPostsPanel(browser);
        posts.searchForPostBySubject(POST_SUBJECT);

        assertTrue(posts.howManyPostsBySubject(POST_SUBJECT) == EXPECTED_COUNT);
    }

    @Test
    public void verifyPostNumbersAdd() throws InterruptedException {
        navigateToPostPage();

        WPPostsPanel posts = new WPPostsPanel(browser);
        int allPosts = posts.getPostsCount();
        int publishedPosts = posts.getPublishedPostsCount();
        int draftPosts = posts.getDraftPostsCount();

        assertTrue(allPosts == draftPosts + publishedPosts);

        Thread.sleep(10000);
    }

    /**
     * An example of an action.
     */
    private void navigateToPostPage() {
        WPLoginPage loginPage = new WPLoginPage(browser);
        loginPage.login(User.ADMIN);

        WPAdminMenu menu = new WPAdminMenu(browser);
        menu.listPosts();
    }

}
