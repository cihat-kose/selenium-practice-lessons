package _11_Windows;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.BaseDriver;

import java.util.List;
import java.util.Set;

public class NewTabWindowTest extends BaseDriver {

    /**
     Task:
     - "https://demoqa.com/browser-windows" adresine gidin.
     - "New Tab" butonuna tıklayın ve yeni sekmede açılan metni konsola yazdırın.

     Adımlar:
     1. Ana sayfaya gidin.
     2. Çerez onayı varsa kapatın.
     3. "New Tab" butonuna tıklayın ve yeni sekmeyi açın.
     4. Yeni sekmede bulunan "This is a sample page" metnini ekrana yazdırın.
     5. Test tamamlandıktan sonra tüm sekmeleri kapatın.
     */

    @Test
    public void newTabWindowTest() {
        // 1. Adım: "https://demoqa.com/browser-windows" adresine gidin.
        driver.get("https://demoqa.com/browser-windows");

        // 2. Adım: Çerez onayı varsa kapat (Çerez çıkmazsa devam et)
        List<WebElement> consent = driver.findElements(By.xpath("//p[@class='fc-button-label']"));
        if (!consent.isEmpty()) {
            consent.get(0).click();  // İlk butona tıklayın
        }

        // 3. Adım: Mevcut pencerenin ID'sini alın (ilk sekme)
        String currentWindowHandle = driver.getWindowHandle();

        // 4. Adım: "New Tab" butonuna tıklayın
        WebElement newTabButton = driver.findElement(By.id("tabButton"));
        newTabButton.click();

        // 5. Adım: Açılan tüm pencere/sekme ID'lerini alın
        Set<String> windowIDs = driver.getWindowHandles();

        // 6. Adım: Yeni açılan sekmeye geçiş yap
        for (String windowID : windowIDs) {
            // Eğer mevcut sekme ise, atla (ilk sekmeyi geç)
            if(windowID.equals(currentWindowHandle)) {
                continue;
            }

            // Yeni sekmeye geçiş yap
            driver.switchTo().window(windowID);
        }

        // 7. Adım: Yeni sekmedeki metni bul ve konsola yazdır
        WebElement newTabText = driver.findElement(By.id("sampleHeading"));
        System.out.println("Yeni sekmedeki metin:" + newTabText.getText());

        // 8. Adım: Test tamamlandıktan sonra tarayıcıyı kapat
        waitAndClose();
    }
}
