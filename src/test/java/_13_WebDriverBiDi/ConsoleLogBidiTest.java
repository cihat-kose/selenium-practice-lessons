package _13_WebDriverBiDi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.bidi.log.ConsoleLogEntry;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/** Demonstrates listening to browser console events with WebDriver BiDi. */
public class ConsoleLogBidiTest {
    private ChromeDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("webSocketUrl", true);
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void receivesConsoleMessageAsBrowserEvent() throws Exception {
        RemoteWebDriver remoteDriver = driver;
        CompletableFuture<ConsoleLogEntry> consoleEvent = new CompletableFuture<>();
        // The handler is released with this WebDriver session; its return type differs across Selenium versions.
        remoteDriver.script().addConsoleMessageHandler(consoleEvent::complete);

        driver.get(getClass().getResource("/webdriver-bidi-example.html").toExternalForm());
        driver.findElement(By.id("write-console-message")).click();

        ConsoleLogEntry entry = consoleEvent.get(5, TimeUnit.SECONDS);
        assertEquals("Hello from WebDriver BiDi", entry.getText());
    }
}
