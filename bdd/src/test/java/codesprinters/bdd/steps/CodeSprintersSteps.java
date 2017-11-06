package codesprinters.bdd.steps;

import codesprinters.bdd.Context;
import codesprinters.bdd.page.CodeSprintersPage;
import codesprinters.bdd.page.GooglePage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michal on 05.06.2017.
 */
public class CodeSprintersSteps {

    Map<String, String> pages;
    
    @Given("^I go to \"([^\"]*)\" webpage$")
    public void iGoToWebpage(String webpage) throws Throwable {
        if (webpage.equals("Google")) {
            GooglePage googlePage = new GooglePage(Context.driver);
            googlePage.goToGoogle();
        }
    }


    @When("^I google for \"([^\"]*)\"$")
    public void iGoogleFor(String query) throws Throwable {
        GooglePage googlePage = new GooglePage(Context.driver);
        googlePage.searchOnGoogle(query);
    }

    @And("^I click link that contains \"([^\"]*)\"$")
    public void iClickLinkThatContains(String pattern) throws Throwable {
        GooglePage googlePage = new GooglePage(Context.driver);
        googlePage.clickOnResultContaining(pattern);
    }

    @Then("^I land at \"([^\"]*)\" url$")
    public void iLandAtUrl(String arg0) throws Throwable {
        WebDriverWait wait = new WebDriverWait(Context.driver, 20);

        wait.until(
                ExpectedConditions.titleIs(
                        CodeSprintersPage.PAGE_TITLE
                )
        );

        String currentURL = Context.driver.getCurrentUrl();

        Assert.assertTrue(currentURL.equals(CodeSprintersPage.URL));
    }


    @And("^I see \"([^\"]*)\" mail address$")
    public void iSeeMailAddress(String mail) throws Throwable {
        CodeSprintersPage codeSprintersPage = new CodeSprintersPage(Context.driver);
        Assert.assertTrue(codeSprintersPage.hasMail(mail));
    }
}
