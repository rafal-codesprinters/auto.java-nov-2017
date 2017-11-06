package codesprinters.pageobjects.page;

import org.openqa.selenium.WebDriver;

/**
 * Created by Michal on 02.06.2017.
 */
public class CodeSprintersPage extends PageObject {

    public static final String PAGE_TITLE = "Code Sprinters -";
    public static final String SEARCH_TERM = "Code Sprinters";
    public static final String URL = "http://agileszkolenia.pl/";

    public static final String EMAIL = "szkolenia@codesprinters.com";

    public CodeSprintersPage(WebDriver driver) {
        super(driver);
    }

    public void goToCodeSprintersPage() {
        driver.get(URL);
    }

}
