package utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.After;
import org.junit.Before;

import java.time.Duration;

public class BaseDriver {

    // Each test instance owns its browser and Selenium helpers.
    public WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor js;

    @Before
    public void setUp() {
        // A fresh browser is created for every test.
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        js = (JavascriptExecutor) driver;
    }

    @After
    public void tearDown() {
        // Guaranteed cleanup also covers tests that fail before waitAndClose().
        WebDriver currentDriver = driver;
        driver = null;
        wait = null;
        js = null;

        if (currentDriver != null) {
            try {
                currentDriver.quit();
            } catch (RuntimeException exception) {
                System.err.println("WebDriver cleanup failed: " + exception.getMessage());
            }
        }
    }

    // Retained by lessons so students can observe the browser before it closes.
    public void waitAndClose() {
        MyFunction.wait(3);  // Keep the result visible for three seconds.
        if (driver != null) {
            WebDriver currentDriver = driver;
            driver = null;
            wait = null;
            js = null;
            currentDriver.quit();       // Tarayıcıyı kapat
        }
    }
}
