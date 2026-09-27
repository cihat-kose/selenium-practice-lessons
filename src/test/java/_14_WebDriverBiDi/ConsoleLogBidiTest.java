package _14_WebDriverBiDi;

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

/**
 * BiDi üzerinden console.log olayını hem yerel fixture'da hem Selenium'un canlı demosunda dinler.
 */
public class ConsoleLogBidiTest {
    private ChromeDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // Tarayıcı ile çift yönlü WebSocket iletişimini aç; BiDi olayları bu kanal üzerinden gelir.
        options.setCapability("webSocketUrl", true);
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        // Her testten sonra Chrome oturumunu kapat.
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void receivesConsoleMessageFromLocalFixture() throws Exception {
        RemoteWebDriver remoteDriver = driver;
        CompletableFuture<ConsoleLogEntry> consoleEvent = new CompletableFuture<>();

        // Olay dinleyicisini, console.log() çağrısından önce kaydet.
        String handlerId = remoteDriver.script().addConsoleMessageHandler(consoleEvent::complete);

        try {
            driver.get(getClass().getResource("/webdriver-bidi-example.html").toExternalForm());
            driver.findElement(By.id("write-console-message")).click();

            // BiDi mesajı callback ile daha sonra gelir; future en fazla 5 saniye bekler.
            ConsoleLogEntry entry = consoleEvent.get(5, TimeUnit.SECONDS);
            assertEquals("Hello from WebDriver BiDi", entry.getText());
        } finally {
            // Handler kimliğini kullanarak olay aboneliğini kaldır.
            remoteDriver.script().removeConsoleMessageHandler(handlerId);
        }
    }

    @Test
    public void receivesConsoleMessageFromSeleniumLiveDemo() throws Exception {
        RemoteWebDriver remoteDriver = driver;
        CompletableFuture<ConsoleLogEntry> consoleEvent = new CompletableFuture<>();

        // Dinleyici tıklamadan önce açık olmalı; aksi halde kısa ömürlü olayı kaçırabiliriz.
        String handlerId = remoteDriver.script().addConsoleMessageHandler(consoleEvent::complete);

        try {
            // Selenium'un canlı demo sayfasındaki consoleLog düğmesi "Hello, world!" üretir.
            driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
            driver.findElement(By.id("consoleLog")).click();

            ConsoleLogEntry entry = consoleEvent.get(5, TimeUnit.SECONDS);
            assertEquals("Hello, world!", entry.getText());
        } finally {
            remoteDriver.script().removeConsoleMessageHandler(handlerId);
        }
    }
}
