package codesprinters.autotest.base.pages;

import codesprinters.autotest.base.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WPAdminMenu extends PageObject {

    private @FindBy(xpath = "//li[@id='menu-posts']/a") WebElement menuPosts;

    public WPAdminMenu(WebDriver browser) {
        super(browser);
        PageFactory.initElements(browser, this);
    }

    public void listPosts() {
        waitForElementToBeClickable(menuPosts);
        menuPosts.click();
    }

}
