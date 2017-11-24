package codesprinters.autotest.base.pages;

import codesprinters.autotest.base.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WPAdminMenu extends PageObject {

    private By menuPosts = By.xpath("//li[@id='menu-posts']/a");

    public WPAdminMenu(WebDriver browser) {
        super(browser);
    }

    public void listPosts() {
        waitForElementToBeClickable(menuPosts);
        browser.findElement(menuPosts).click();
    }

}
