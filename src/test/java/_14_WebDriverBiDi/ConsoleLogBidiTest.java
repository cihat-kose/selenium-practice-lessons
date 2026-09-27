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
 * WebDriver BiDi ile tarayıcı konsol olayını dinler ve olay mesajını doğrular.
 * Olayı tetikleyen yerel HTML fixture'ını kullanır.
 */
public class ConsoleLogBidiTest {
    private ChromeDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // BiDi olayları için Chrome oturumunda WebSocket uç noktasını etkinleştir.
        options.setCapability("webSocketUrl", true);
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        // Başarısızlık durumunda da Chrome oturumunun açık kalmasını önle.
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void receivesConsoleMessageAsBrowserEvent() throws Exception {
        RemoteWebDriver remoteDriver = driver;
        CompletableFuture<ConsoleLogEntry> consoleEvent = new CompletableFuture<>();

        // Olay dinleyicisini, mesajı üretecek butona tıklamadan önce kaydet.
        // Dönüş değerini saklamıyoruz; handler'ın ömrü bu WebDriver oturumuna bağlıdır.
        remoteDriver.script().addConsoleMessageHandler(consoleEvent::complete);

        // Yerel sayfadaki buton JavaScript console.log() çağrısı yapar.
        driver.get(getClass().getResource("/webdriver-bidi-example.html").toExternalForm());
        driver.findElement(By.id("write-console-message")).click();

        // Asenkron BiDi olayını en fazla 5 saniye bekle; gelmezse test zaman aşımıyla başarısız olur.
        ConsoleLogEntry entry = consoleEvent.get(5, TimeUnit.SECONDS);

        // Tarayıcıdan gelen olayın beklenen konsol mesajını taşıdığını doğrula.
        assertEquals("Hello from WebDriver BiDi", entry.getText());
    }
}
