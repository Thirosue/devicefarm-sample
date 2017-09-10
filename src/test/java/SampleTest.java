import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * Simple test which demonstrates how a test can be run against Mobile Safari running on an Appium instance.
 *
 * The test is based on https://github.com/appium/appium/blob/master/sample-code/examples/node/safari.js
 *
 * @author Takeshi Hirosue
 */
public class SampleTest {

    private RemoteWebDriver driver;

    /**
     * Instantiates the {@link #driver} instance by using DesiredCapabilities which specify the
     * 'iPhone Simulator' device and 'safari' app.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setPlatform(Platform.IOS);
        capabilities.setBrowserName(Browse.safari.name());
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"),
                capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Configuration.browser = driver.getClass().getName();
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
        driver.get("https://www.google.co.jp/");
        Thread.sleep(1000);

        System.out.println(driver.getTitle());
        System.out.println(driver.getPageSource());

        driver.findElement(By.id("lst-ib")).sendKeys("AWS DeviceFarm");
        driver.findElement(By.id("tsbb")).click();
        assertTrue(takeScreenshot("index"));

        assertEquals("AWS DeviceFarm", driver.findElement(By.id("lst-ib")).getAttribute("value"));
        assertTrue(takeScreenshot("result"));
    }
}
