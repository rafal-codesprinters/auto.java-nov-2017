package codesprinters.pageobjects;

import codesprinters.pageobjects.page.CodeSprintersPage;
import codesprinters.pageobjects.page.GooglePage;
import com.googlecode.junittoolbox.ParallelRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Michal on 02.06.2017.
 */
@RunWith(ParallelRunner.class)
public class CodeSprintersPageTest extends TestBase {

    @Test
    public void verifyIfGoogleFindsCodeSprinters() {

        GooglePage googlePage = new GooglePage(this.driver);

        logger.info(TC() + "Looking for Code Sprinters page on Google");
        googlePage.googleFor(
                CodeSprintersPage.SEARCH_TERM,
                CodeSprintersPage.PAGE_TITLE
        );
        logger.info(TC() + "Going to Code Sprinters page on Google");

        WebDriverWait wait = new WebDriverWait(driver, 20);

        logger.info(TC() + "Waiting for Code Sprinters page to load");
        wait.until(
                ExpectedConditions.titleIs(
                        CodeSprintersPage.PAGE_TITLE
                )
        );
        logger.info(TC() + "Code Sprinters page apears to be loaded");

        logger.info(TC() + "Checking URL");
        String currentURL = driver.getCurrentUrl();
        logger.info(TC() + "Current URL is " + currentURL);

        logger.info(TC() + "Current URL is " + currentURL);
        logger.info(TC() + "Comparing against " + CodeSprintersPage.URL);

        Assert.assertTrue(
                currentURL.equals(CodeSprintersPage.URL)
        );

        logger.info(TC() + "All asserts passed");
    }

    @Test
    public void verifyIfCodeSprintersPageHasValidEmail() {
        CodeSprintersPage codeSprintersPage = new CodeSprintersPage(driver);

        logger.info(TC() + "Loading Code Sprinters page using URL=" + CodeSprintersPage.URL);
        codeSprintersPage.goToCodeSprintersPage();

        Assert.assertTrue(
            codeSprintersPage.hasMail(CodeSprintersPage.EMAIL)
        );

        logger.info(TC() + "All asserts passed");
    }

}
