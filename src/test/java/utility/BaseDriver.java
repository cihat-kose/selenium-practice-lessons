package utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.SessionNotCreatedException;
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
        // Her test için temiz bir Chrome oturumu oluştur. Chrome bazen ilk başlatmada
        // process'i kapatabildiği için yalnızca oturum oluşturma hatasında bir kez dene.
        try {
            driver = new ChromeDriver();
        } catch (SessionNotCreatedException firstAttempt) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interrupted) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Chrome yeniden denemesi kesintiye uğradı.", interrupted);
            }

            try {
                driver = new ChromeDriver();
            } catch (SessionNotCreatedException retryFailure) {
                retryFailure.addSuppressed(firstAttempt);
                throw retryFailure;
            }
        }
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

    /**
     * Test sonucunu öğrencinin inceleyebilmesi için tarayıcıyı kısa süre açık tutup kapatır.
     * Bu bekleme yalnızca ekranda gözlem içindir; test senkronizasyonu için WebDriverWait kullanılmalıdır.
     */
    public void waitAndClose() {
        MyFunction.wait(3);  // Sonuç ekranını inceleyebilmek için tarayıcıyı 3 saniye açık tut.
        if (driver != null) {
            WebDriver currentDriver = driver;
            driver = null;
            wait = null;
            js = null;
            currentDriver.quit();       // Tarayıcıyı kapat
        }
    }
}
