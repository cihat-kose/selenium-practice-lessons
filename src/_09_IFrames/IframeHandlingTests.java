package _09_IFrames;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

import java.util.List;

public class IframeHandlingTests extends BaseDriver {

    /**
     Task 1:

     - "https://demoqa.com/frames" adresine gidin.
     - Sayfadaki iframe sayısını bulun ve ekrana yazdırın.
     */
    @Test
    public void printIframeCount() {
        // 1. Adım: Web sitesine git
        driver.get("https://demoqa.com/frames");

        // 2. Adım: Eğer varsa çerez uyarısını kapatın
        List<WebElement> consent = driver.findElements(By.xpath("//button[@class='fc-button fc-cta-consent fc-primary-button']//p"));
        if (!consent.isEmpty()) {
            consent.get(0).click();
        }

        // 3. Adım: Iframe'lerin yüklenmesini bekleyin
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe")));

        // 4. Adım: Sayfadaki iframe'leri bulun
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

        // 5. Adım: Iframe sayısını ekrana yazdırın
        System.out.println("Sayfadaki iframe sayısı: " + iframes.size());

        // 6. Adım: Test tamamlandıktan sonra tarayıcıyı kapatın
        waitAndClose();
    }

    /**
     Task 2:

     - "https://demoqa.com/frames" adresine gidin.
     - Iframe içindeki "This is a sample page" metnini ekrana yazdırın.
     */
    @Test
    public void printIframeText() {
        // 1. Adım: Web sitesine git
        driver.get("https://demoqa.com/frames");

        // 2. Adım: Eğer varsa çerez uyarısını kapatın
        List<WebElement> consent = driver.findElements(By.xpath("//button[@class='fc-button fc-cta-consent fc-primary-button']//p"));
        if (!consent.isEmpty()) {
            consent.get(0).click();
        }

        // 3. Adım: Iframe'e geçiş yap (id kullanarak)
        WebElement iframe = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(iframe);  // WebElement ile iframe'e geçiş

        // 4. Adım: Iframe içindeki "This is a sample page" metnini bul ve ekrana yazdır
        WebElement text = driver.findElement(By.id("sampleHeading"));
        System.out.println("Iframe içindeki metin: " + text.getText());

        // 5. Adım: Test tamamlandıktan sonra tarayıcıyı kapatın
        waitAndClose();
    }
}
