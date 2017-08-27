import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 *
 * Simple test which demonstrates how a test can be run against Mobile Safari running on an Appium instance.
 *ß
 * The test is based on https://github.com/appium/appium/blob/master/sample-code/examples/node/safari.js
 *
 * @author Takeshi Hirosue
 */
public class SampleTest {

    private WebDriver driver;

    /**
     * Instantiates the {@link #driver} instance by using DesiredCapabilities which specify the
     * 'iPhone Simulator' device and 'safari' app.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("deviceName", "iPhone SE");
//        capabilities.setCapability("platformName", "iOS");
//        capabilities.setCapability("platformVersion", "10.3");
//        capabilities.setCapability("browserName", "safari");
        driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),
                new DesiredCapabilities());
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    /**
     * Closes the {@link #driver} instance.
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    public boolean takeScreenshot(final String name) {
        String screenshotDirectory = System.getProperty("appium.screenshots.dir", System.getProperty("java.io.tmpdir", ""));
        System.out.println(screenshotDirectory);
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        return screenshot.renameTo(new File(screenshotDirectory, String.format("%s.png", name)));
    }

    /**
     * Navigates to http://saucelabs.com/test/guinea-pig and interacts with the browser.
     *
     * @throws Exception
     */
    @Test
    public void runTest() throws Exception {
        driver.get("http://www.stockchat.io/");

        Thread.sleep(1000);
        System.out.println(driver.getTitle());

        assertTrue(takeScreenshot("index"));

        driver.getPageSource();

        System.out.println(driver.getCurrentUrl());
    }
}
