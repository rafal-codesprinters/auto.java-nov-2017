package codesprinters.autotest.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class that should be the parent of any PageObject class.
 * Supports PageFactory approach to building Page Objects.
 */
public abstract class PageObject {

    protected WebDriver browser;

    public PageObject(WebDriver browser) {
        this.browser = browser;
    }

    /**
     * Waits for maximum of 15 seconds for elelemnt to be clickable.
     * @param element handler to element
     */
    protected void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(browser, 15);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Extracts value surounded by brackets so (123) -> 123
     * @param element webelement that is a source of a value
     * @return if element has text that contains (x..y..z) it wil be x...y...z after this method is called
     */
    protected int getValueFromBrackets(WebElement element) {
        String bValue = element.getText();
        return Integer.parseInt(bValue.replace("(", "").replace(")", ""));
    }

}
