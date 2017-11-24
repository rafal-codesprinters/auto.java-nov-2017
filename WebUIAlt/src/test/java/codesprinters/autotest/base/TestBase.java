package codesprinters.autotest.base;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Base class that should be the parent or ancestor to any other classes with @Test annotated methods
 * Test framework assumes that chromedriver.exe and geckodriver.exe are located on system's path
 */
public abstract class TestBase {

    protected WebDriver browser;

    private String baseURL;

    @Before
    public void initDriver() throws IOException {

        /* Let's load properties from test.properties file */
        Properties props = new Properties();
        props.load(TestBase.class.getClassLoader().getResourceAsStream("test.properties")); // Trying to load properties

        /* Get test environemnt URL from config */
        baseURL = props.getProperty("test.url");

        if (baseURL == null) throw new InvalidParameterException("Missing environment URL.");

        String target = props.getProperty("target");

        if (null == target)
            throw new InvalidParameterException("Missing operation mode, one of (grid|chrome|firefox) is required.");

        if (target.equals("grid")) {
            String hub = props.getProperty("grid.hub");
            String port = props.getProperty("grid.hub.port");
            String mode = props.getProperty("grid.hub.mode");

            if (hub == null || "".equals(hub)) throw new InvalidParameterException("Missing grid hub URL!");
            if (port == null || "".equals(port)) throw new InvalidParameterException("Missing grid hub port!");
            if (mode == null || "".equals(mode))
                throw new InvalidParameterException("Missing grid remote browser type!");

            String grid = "http://" + hub + ":" + port + "/wd/hub";

            DesiredCapabilities capabilities;

            if (mode.equals("chrome")) {
                capabilities = DesiredCapabilities.chrome();
                /* If any additional remote chrome setup is needed this code goes here! */
            } else if (mode.equals("firefox")) {
                capabilities = DesiredCapabilities.firefox();
                /* If any additional remote firefox setup is needed this code goes here! */
            } else
                throw new InvalidParameterException("Unknown grid remote browser type! Please use (chrome|firefox)");

            browser = new RemoteWebDriver(new URL(grid), capabilities);

        } else {
            if (target.equals("chrome")) {
                /* If any additional local chrome setup is needed this code goes here! */
                browser = new ChromeDriver();
            } else if (target.equals("firefox")) {
                /* If any additional local firefox setup is needed this code goes here! */
                browser = new FirefoxDriver();
            } else {
                throw new InvalidParameterException("Unknown browser type: " + target + " - please use (grid|chrome|firefox).");
            }
        }

        if (browser == null) throw new RuntimeException("WebDriver failed to initialize. Check your config!");

        browser.manage().window().maximize();
        browser.manage().deleteAllCookies();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @After
    public void cleanUp() {
        if (browser != null) {
            browser.quit();
        }
    }

    public String getLoginURL() {
        return baseURL + "/wp-login.php";
    }

}