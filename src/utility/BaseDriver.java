package utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BaseDriver {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static JavascriptExecutor js;

    /**
     * Statik Blok
     *
     * Sınıf ilk kez yüklendiğinde otomatik olarak çalışır.
     * WebDriver ve yardımcı nesneler burada bir kez oluşturulur;
     * tüm test sınıfları bu ortak yapıyı miras alarak kullanır.
     *
     * CI Ortamı Nedir?
     * GitHub Actions gibi sürekli entegrasyon (CI) araçları,
     * testleri gerçek bir ekran olmadan çalıştırır.
     * Bu nedenle tarayıcının "headless" (görünmez) modda açılması gerekir.
     * GitHub Actions, çalışma ortamına otomatik olarak "CI=true" değişkeni ekler.
     * Biz de bunu okuyarak ortama uygun ayar yapıyoruz.
     */
    static {

        ChromeOptions options = new ChromeOptions();

        /**
         * System.getenv("CI") — Ortam Değişkeni Okuma
         *
         * CI değişkeni tanımlıysa → GitHub Actions (veya benzeri CI) ortamındayız demektir.
         * CI değişkeni null ise → kendi bilgisayarımızda çalışıyoruz demektir.
         *
         * Headless mod: Tarayıcı arayüzü açılmadan arka planda çalışır.
         * Bu mod olmadan CI ortamında "no display" hatası alınır.
         */
        if (System.getenv("CI") != null) {
            // CI ortamı: GitHub Actions, Jenkins vb.
            options.addArguments("--headless");              // Tarayıcı arayüzü açılmaz
            options.addArguments("--no-sandbox");            // Linux güvenlik kısıtlamasını devre dışı bırakır
            options.addArguments("--disable-dev-shm-usage"); // Bellek sorunlarını önler (Docker/Linux için)
            options.addArguments("--window-size=1920,1080"); // Sanal ekran boyutu tanımlanır
        }

        /**
         * ChromeDriver Başlatma
         *
         * Selenium 4.6+ sürümünden itibaren Selenium Manager sayesinde
         * ChromeDriver manuel olarak indirilmek zorunda değildir.
         * Yüklü Chrome sürümüne uygun driver otomatik olarak bulunur ve kullanılır.
         */
        driver = new ChromeDriver(options);

        /**
         * Pencere Yönetimi
         *
         * Lokal ortamda tarayıcıyı tam ekran açarız.
         * CI ortamında ekran olmadığı için maximize() yerine
         * yukarıda window-size argümanıyla boyut belirledik.
         */
        if (System.getenv("CI") == null) {
            driver.manage().window().maximize();
        }

        /**
         * pageLoadTimeout — Sayfa Yükleme Zaman Aşımı
         *
         * Tarayıcının bir sayfayı tamamen yüklemesi için tanınan maksimum süredir.
         * Bu süre içinde sayfa yüklenmezse TimeoutException fırlatılır.
         * Örnek: Yavaş bir ağda 30 saniye geçerse test başarısız sayılır.
         */
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        /**
         * implicitlyWait — Zımni (Örtük) Bekleme
         *
         * WebDriver bir elementi bulamadığında hemen hata fırlatmaz;
         * belirtilen süre boyunca periyodik olarak aramaya devam eder.
         * Süre sonunda hâlâ bulunamazsa NoSuchElementException fırlatılır.
         *
         * Not: implicitlyWait ve WebDriverWait (explicit wait) birlikte kullanımı
         * beklenmedik davranışlara yol açabilir. İleri seviyelerde sadece
         * explicit wait kullanımı tercih edilir.
         */
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        /**
         * WebDriverWait — Açık (Explicit) Bekleme
         *
         * Belirli bir koşulun gerçekleşmesini beklemek için kullanılır.
         * implicitlyWait'ten farkı: element bulunana kadar değil,
         * tam olarak tanımladığımız koşul sağlanana kadar bekler.
         * Örnek: wait.until(ExpectedConditions.visibilityOfElementLocated(...))
         */
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        /**
         * JavascriptExecutor
         *
         * Selenium'un doğrudan erişemediği durumlarda
         * sayfada JavaScript kodu çalıştırmamızı sağlar.
         * Örnek kullanım: js.executeScript("window.scrollTo(0, 500)")
         * ya da görünmez elementlere tıklamak için kullanılır.
         */
        js = (JavascriptExecutor) driver;
    }

    /**
     * waitAndClose() — Bekle ve Kapat
     *
     * Testi sonlandırmadan önce kısa bir bekleme yaparak
     * sonucu gözlemleme imkânı tanır (lokal ortamda kullanışlıdır).
     * driver.quit() hem tarayıcıyı hem de WebDriver sürecini tamamen sonlandırır.
     *
     * Not: driver.close() yalnızca aktif sekmeyi kapatır;
     * driver.quit() ise tüm oturumu ve süreci bitirir.
     */
    public static void waitAndClose() {
        MyFunction.wait(3);
        driver.quit();
    }
}
