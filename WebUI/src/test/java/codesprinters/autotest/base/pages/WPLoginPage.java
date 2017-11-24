package codesprinters.autotest.base.pages;

import codesprinters.autotest.base.PageObject;
import codesprinters.autotest.domain.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WPLoginPage extends PageObject {

    private By userNameTextBox = By.id("usernameOrEmail");
    private By passwordTextBox = By.id("password");
    private By submitButton = By.xpath("//div[@class='login__form-action']/button");

    public WPLoginPage(WebDriver browser) {
        super(browser);
    }

    private void fillUserName(String userName) {
        browser.findElement(userNameTextBox).sendKeys(userName);
    }

    private void fillPassword(String pass) {
        browser.findElement(passwordTextBox).sendKeys(pass);
    }

    private void submitForm() {
        browser.findElement(submitButton).click();
    }

    public void login(User user) {
        fillUserName(user.getUserName());
        fillPassword(user.getPassword());
        submitForm();
    }

}
