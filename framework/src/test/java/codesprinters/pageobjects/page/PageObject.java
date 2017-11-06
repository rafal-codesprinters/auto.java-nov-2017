package codesprinters.pageobjects.page;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Michal on 02.06.2017.
 */
public abstract class PageObject {

    protected WebDriver driver;

    public PageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean hasMail(String mail) {
        try {
            driver.findElement(getMailSelector(mail));
            return true;
        } catch (ElementNotFoundException e) {
            return false;
        }
    }

    private By getMailSelector(String mail) {
        return By.xpath("//a[@href='mailto:" + mail + "']");
    }

}
