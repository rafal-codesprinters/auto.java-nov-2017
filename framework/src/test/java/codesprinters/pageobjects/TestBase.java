package codesprinters.pageobjects;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Michal on 02.06.2017.
 */

public abstract class TestBase {

    private static Properties props; // Property storage

    /* Static code block is executed when class is loaded.
     * Here i is used to load test.properties and to initialize configuration of tests.
     */
    static {
        System.setProperty("log.timestamp", "" + System.currentTimeMillis()); // Setting up unique log file name part.
        props = new Properties();
        try {
            props.load(TestBase.class.getClassLoader().getResourceAsStream("test.properties")); // Trying to load properties
        } catch (IOException e) {
            // If load failed setup some default values
            if (!props.containsKey("browser.type")) props.setProperty("browser.type", "firefox");
            if (!props.containsKey("grid.url")) props.setProperty("grid.url", "http://localhost:1234/wd/hub");
        }
    }

    private final int DEFAULT_TIMEOUT = 3; //Default timeout

    @Rule
    public TestName name = new TestName(); // Making test case aware of its name


    protected WebDriver driver; // Driver that will be used to communicate between browser and test case

    protected Logger logger = LoggerFactory.getLogger(this.getClass()); // Initializing Logger

    /**
     * @return customizable test case prefix used in logger
     */
    protected String TC() {
        return "[[[" + name.getMethodName() + "]]] ";
    }

    /**
     * Block that is used to initialize every single test case instance.
     * @throws Exception on failed initialization we throw exception to be caught by JUnit.
     */
    @Before
    public void testSetup() throws Exception {

        String browserType = props.getProperty("browser.type"); // Check properties for browser type.

        logger.info(TC() + "Initializing test.");
        logger.info(TC() + "Using " + browserType + " browser");

        // Build correct driver based on test configuration.
        if (browserType.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserType.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browserType.equals("grid")) {
            // If we use grid, get configuration of a hub.
            String gridURL = props.getProperty("grid.url");

            logger.info(TC() + "trying to use Selenium GRID on :" + gridURL);
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            driver = new RemoteWebDriver(new URL(gridURL), capabilities);
        }

        logger.info(TC() + "New " + driver.getClass().getSimpleName() + " was created.");

        driver.manage().deleteAllCookies();
        logger.info(TC() + "All cookies deleted.");

        driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);  // Set implicit wait.
        logger.info(TC() + "Setting implicit wait to " + DEFAULT_TIMEOUT + " " + TimeUnit.SECONDS.name());
        driver.manage().window().maximize();

        logger.info(TC() + "Test initialized!");
    }

    @After
    public void testTeardown() {
        logger.info(TC() + "Finalizing test");
        if (driver != null) {
            logger.info(TC() + "Closing webdriver " + driver.getClass().getSimpleName());
            driver.quit(); // Close driver.
        }
        logger.info(TC() + "Test finished");
    }

}
