package codesprinters.autotest.base.pages;

import codesprinters.autotest.base.PageObject;
import codesprinters.autotest.domain.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WPLoginPage extends PageObject {

    private @FindBy(id = "usernameOrEmail") WebElement userNameTextBox;
    private @FindBy(id = "password") WebElement passwordTextBox;
    private @FindBy(xpath = "//div[@class='login__form-action']/button") WebElement submitButton;

    public WPLoginPage(WebDriver browser) {
        super(browser);
        PageFactory.initElements(browser, this);
    }

    private void fillUserName(String userName) {
        userNameTextBox.sendKeys(userName);
    }

    private void fillPassword(String pass) {
        passwordTextBox.sendKeys(pass);
    }

    private void submitForm() {
        submitButton.click();
    }

    public void login(User user) {
        fillUserName(user.getUserName());
        fillPassword(user.getPassword());
        submitForm();
    }

}
