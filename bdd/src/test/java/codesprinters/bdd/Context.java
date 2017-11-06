package codesprinters.bdd;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Michal on 05.06.2017.
 */
public class Context {

    private static Properties props; // Property storage

    static {
        props = new Properties();
        try {
            props.load(Context.class.getClassLoader().getResourceAsStream("test.properties")); // Trying to load properties
        } catch (IOException e) {
            // If load failed setup some default values
            if (!props.containsKey("browser.type")) props.setProperty("browser.type", "firefox");
        }
    }

    private final int DEFAULT_TIMEOUT = 3; //Default timeout

    @Rule
    public TestName name = new TestName(); // Making test case aware of its name

    public static WebDriver driver; // Driver that will be used to communicate between browser and test case

    /**
     * Block that is used to initialize every single test case instance.
     * @throws Exception on failed initialization we throw exception to be caught by JUnit.
     */
    @Before
    public void before(Scenario scenario) throws Exception {

        String browserType = props.getProperty("browser.type"); // Check properties for browser type.

        // Build correct driver based on test configuration.
        if (browserType.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserType.equals("chrome")) {
            driver = new ChromeDriver();
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);  // Set implicit wait.
        driver.manage().window().maximize();
    }

    @After
    public void testTeardown() {
        if (driver != null) {
            driver.quit(); // Close driver.
        }
    }

}
