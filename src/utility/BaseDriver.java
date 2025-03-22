package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseDriver {

    public static WebDriver driver;
    public static WebDriverWait wait;

    // Statik blok - WebDriver ve diğer yapılandırmalar burada tanımlanıyor
    static {
        // ChromeDriver başlatılır ve temel ayarlamalar yapılır
        driver = new ChromeDriver();
        driver.manage().window().maximize();  // Tarayıcı tam ekran yapılır

        // Sayfa yüklenmesi için maksimum bekleme süresi (30 saniye)
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        /**
         * pageLoadTimeout - WebDriver'ın bir sayfanın tamamen yüklenmesini beklemesini sağlar.
         * Eğer sayfa belirtilen süre içinde yüklenmezse, TimeoutException hatası fırlatılır.
         * Örnek: 30 saniye içinde sayfa yüklenmezse, hata fırlatılır.
         */

        // Web elementlerin yüklenmesi için zımni bekleme süresi (30 saniye)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        /**
         * implicitlyWait - Sayfadaki öğelerin bulunması için belirli bir süre beklemeyi sağlar.
         * Bu süre zarfında öğe bulunamazsa, WebDriver bu süre kadar arama yapmaya devam eder.
         */

        // Explicit wait ve JavascriptExecutor tanımlanır
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Tarayıcıyı belirli bir süre bekleyip kapatan metod
    public static void waitAndClose() {
        MyFunction.wait(3);  // 3 saniye bekleme
        driver.quit();       // Tarayıcıyı kapat
    }
}

/**
 * BaseDriver Sınıfı:
 * Bu sınıf, WebDriver'ın temel yapılandırmalarını içeren bir altyapı sağlar.
 * - ChromeDriver kullanılarak tarayıcı başlatılır ve yönetilir.
 * - Sayfa yükleme süresi ve zımni bekleme süreleri ayarlanmıştır.
 * - WebDriverWait ve JavascriptExecutor objeleri de global olarak tanımlanmıştır.
 * <p>
 * waitAndClose() metodu, belirli bir süre bekledikten sonra tarayıcıyı kapatır.
 */
