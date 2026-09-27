package _08_Waits;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

import java.util.List;

public class GoogleExplicitWaitExample extends BaseDriver {

    /**
     Task:
     Google Arama Sonuçlarında Selenium Kelimesini Doğrulama

     Görev Adımları:
     1. Web Sitesine Gidin:
     - "https://google.com/" sitesine gidiniz.

     2. Arama Kutusuna Metin Girin ve Enter'a Basın:
     - Google'ın arama kutusuna "Selenium" yazın ve **Enter** tuşuna basın.

     3. Arama Sonuçlarından İlk Sonucu Doğrulama:
     - Arama sonuçları sayfasındaki ilk sonucun "selenium" kelimesini içerdiğini doğrulayın.
     - İlk arama sonucunun başlığını kontrol edin ve bu başlığın "selenium" kelimesini içerip içermediğini doğrulayın.

     4. Testi Sonlandırın:
     - Sonuçların doğru olduğunu doğruladıktan sonra, testi tamamlayın ve tarayıcıyı kapatın.
     */

    @Test
    public void searchAndVerifySeleniumResult() {
        // 1. Adım: Google'a git
        driver.get("https://google.com/");

        // 2. Adım: "Tümünü reddet" butonu varsa tıklayın (çerez uyarısı)
        List<WebElement> rejectAll = driver.findElements(By.xpath("//button[@id='W0wltc']/div"));
        if (!rejectAll.isEmpty()) {
            rejectAll.get(0).click();
        }

        // 3. Adım: Arama kutusunu bulun ve "Selenium" yazın, ardından Enter'a basın
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys("selenium" + Keys.ENTER);

        // 4. Adım: İlk arama sonucunu bul ve tıklanabilir olmasını bekle
        WebElement theFirstLink = driver.findElement(By.xpath("(//h3)[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(theFirstLink));  // Explicit Wait kullanarak tıklanabilir olmasını bekle

        // 5. Adım: İlk arama sonucunun "Selenium" kelimesini içerip içermediğini doğrula
        String firstResultText = theFirstLink.getText().toLowerCase();
        System.out.println("İlk Sonuç: " + firstResultText);

        // Başlığın içinde "selenium" kelimesinin olup olmadığını doğrula (küçük/büyük harf farkını dikkate almadan)
        Assert.assertTrue("Arama sonucu 'Selenium' içermiyor!", firstResultText.contains("selenium"));

        // 6. Adım: Testi kapat
        waitAndClose();
    }
}
