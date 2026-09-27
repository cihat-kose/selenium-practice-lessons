package _08_Waits;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import utility.BaseDriver;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class FluentWaitExampleJUnit extends BaseDriver {

    /**
     Task: Fluent Wait ile Dinamik İçerik Bekleme

     Görev Adımları:
     1. Web Sitesine Gidin: "https://the-internet.herokuapp.com/dynamic_loading/1" sitesine gidiniz.
     2. "Start" Butonuna Tıklayın: Dinamik bir içerik yüklemeye başlayacaktır.
     3. Fluent Wait ile "Hello World!" Yazısını Bekleyin: 15 saniye boyunca, her 2 saniyede bir yazının görünüp görünmediğini kontrol edin.
     4. Bekleme Sonucu: Başarı veya başarısızlık sonucunu konsola yazdırın.
     5. Testi Sonlandırın: Tarayıcıyı kapatın.
     */

    @Test
    public void fluentWaitTest() {
        // 1. Adım: Web sitesine git
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        // 2. Adım: "Start" butonuna tıkla ve dinamik içeriğin yüklenmesini başlat
        WebElement startButton = driver.findElement(By.xpath("//button[text()='Start']"));
        startButton.click();

        /**
         3. Adım: Fluent Wait tanımlama
         Fluent Wait'i burada şu şekilde tanımlıyoruz:

         1)- withTimeout(Duration.ofSeconds(15)): Maksimum bekleme süresi olarak 15 saniye belirliyoruz.
         Bu, Fluent Wait'in 15 saniye boyunca koşulu kontrol edeceği anlamına gelir.

         2)- pollingEvery(Duration.ofSeconds(2)): Bekleme süresi boyunca her 2 saniyede bir sayfadaki
         "Hello World!" yazısının görünür olup olmadığını kontrol ediyoruz.

         3)- ignoring(NoSuchElementException.class): Eğer bu süreçte element bulunamazsa
         (örneğin, element henüz yüklenmemişse), NoSuchElementException hatasını göz ardı ediyoruz
         ve tekrar denemeye devam ediyoruz.
         */
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(15))      // Maksimum bekleme süresi
                .pollingEvery(Duration.ofSeconds(2))      // Her 2 saniyede bir kontrol et
                .ignoring(NoSuchElementException.class);  // Eğer element yoksa hata verme, devam et

        /**
         4. Adım: "Hello World!" yazısının görünmesini bekleme
         Bu adımda, Fluent Wait ile "Hello World!" yazısının görünür hale gelmesini bekliyoruz.

         1)- apply() metodu:
         a) WebDriver, belirli periyotlarda (2 saniyede bir) sayfadaki "Hello World!" yazısını kontrol eder.
         b) Eğer yazı görünür hale gelirse (isDisplayed()), element döndürülür.
         c) Eğer yazı görünmezse, null döndürülür ve Fluent Wait tekrar denemeye devam eder.

         2)- Fluent Wait bu işlemi 15 saniye boyunca yapar.
         Eğer 15 saniye içinde yazı görünür hale gelmezse, test başarısız olur.
         */
        WebElement helloWorldText = fluentWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(By.xpath("//h4[text()='Hello World!']"));
                if (element.isDisplayed()) {
                    return element;  // Eğer görünürse, element döndür
                }
                return null;  // Aksi halde null döndür, tekrar deneyecek
            }
        });

        /**
         5. Adım: Testin sonucunu JUnit ile kontrol et
         Eğer helloWorldText null değilse (yani "Hello World!" yazısı bulunduysa ve görünürse),
         test başarıyla sonuçlanır. Null ise test JUnit tarafından başarısız kabul edilir.
         */
        Assert.assertNotNull("Test Başarısız: 'Hello World!' yazısı görünmedi.", helloWorldText);
        Assert.assertTrue("'Hello World!' yazısı görünmedi!", helloWorldText.isDisplayed());

        System.out.println("Test Başarılı: 'Hello World!' yazısı göründü.");

        // 6. Adım: Tarayıcıyı kapat
        waitAndClose();

        /**
         Neden Fluent Wait Kullandık?

         Dinamik İçerik: Bu sayfada, içerik (yani "Hello World!" yazısı) dinamik olarak yükleniyor.
         Yani, sayfa yüklendiğinde hemen görünür olmayabilir.
         Butona tıkladıktan sonra belirli bir süre beklemek gerekiyor.
         İşte bu gibi durumlarda, Fluent Wait dinamik içeriklerin yüklenmesini beklemek için idealdir.

         Periyodik Kontrol:
         Fluent Wait ile her 2 saniyede bir sayfadaki elementi kontrol edebiliyoruz.
         Bu, elementin belirli bir anda yüklenip yüklenmediğini kontrol etmek için idealdir.
         Eğer element 15 saniye içinde yüklenmezse, Fluent Wait zaman aşımına uğrar ve test başarısız olur.

         Esneklik:
         Fluent Wait, element henüz mevcut değilse hata atmadan bekleme yapmamıza olanak tanır.
         NoSuchElementException hatasını göz ardı ederek, elementin yavaş yüklenmesi durumunda tekrar denemeye devam eder.
         Bu da esnek beklemeler yapmamızı sağlar.

         Fluent Wait Kullanımı vs. Explicit Wait:

         Explicit Wait ile belirli bir koşulun sağlanmasını bekleriz,
         ancak Fluent Wait bu bekleme işlemini daha esnek ve kontrol edilebilir bir hale getirir.

         Polling Time (Her 2 saniyede bir kontrol etme) özelliği ile Fluent Wait,
         elementin ne zaman yükleneceğini bilmiyorsak,
         belirli periyotlarla tekrar kontrol ederek daha sağlam bir bekleme stratejisi sunar.
         Ayrıca, Fluent Wait ile istisnaları yönetebiliriz.
         Örneğin, NoSuchElementException gibi hatalar fırlatılmadan göz ardı edilebilir
         ve elementin gelmesini sabırla bekleyebiliriz.

         Sonuç:
         Bu testte, Fluent Wait kullanarak, bir elementin dinamik olarak yüklenmesini ve görünmesini bekledik.
         Fluent Wait, özellikle dinamik web sayfalarında ve
         belirli bir süre boyunca düzenli kontrol yapılması gereken durumlarda kullanılmalıdır.
         Bu örnekte, "Hello World!" yazısı dinamik olarak yüklendiği için Fluent Wait kullanımı doğru bir seçimdir.
         */
    }
}
