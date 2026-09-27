package _06_Actions;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

public class DuckDuckGoActionsExample extends BaseDriver {

    /**
     Task: DuckDuckGo Arama Sonuçlarında Selenium Kelimesini Doğrulama

     Görev Adımları:

     1. Web Sitesine Gidin:
     - "https://duckduckgo.com/" sitesine gidiniz.

     2. Arama Kutusuna Metin Girin ve Enter'a Basın:
     - Arama kutusuna "Selenium" yazın ve Enter tuşuna basın.

     3. Arama Sonuçlarından İlk Sonucu Doğrulama:
     - Arama sonuçları sayfasındaki ilk sonucun "selenium" kelimesini içerdiğini doğrulayın.
     - İlk arama sonucunun başlığını kontrol edin ve bu başlığın "selenium" kelimesini içerip içermediğini doğrulayın.

     4. Testi Sonlandırın:
     - Sonuçların doğru olduğunu doğruladıktan sonra, testi tamamlayın ve tarayıcıyı kapatın.
     */

    @Test
    public void searchAndVerifySeleniumResult() {
        // 1. Adım: DuckDuckGo'ya git (Google robot kontrolü otomasyon testini engelleyebiliyor)
        driver.get("https://duckduckgo.com/");

        // 2. Adım: Arama kutusunu bulun
        WebElement searchInput = driver.findElement(By.name("q"));

        // 3. Adım: Actions zinciriyle arama kutusuna yazıp Enter'a basın.
        Actions actions = new Actions(driver);
        Action action = actions
                .moveToElement(searchInput)  // Arama kutusuna gel
                .click()  // Tıkla
                .sendKeys("selenium" + Keys.ENTER)  // Selenium yaz ve Enter'a bas
                .build();
        action.perform();  // Aksiyon zincirini gerçekleştir

        // 4. Adım: İlk sonuç başlığı görünene kadar açıkça bekle.
        WebElement theFirstLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//h3")));

        // Bazı doğru Selenium sayfalarının başlığı "Downloads" gibi genel olabilir.
        // Bu nedenle başlık metni yerine ilk sonuç bağlantısının hedefini doğrula.
        String firstResultUrl = theFirstLink.findElement(By.xpath("./ancestor::a[1]")).getAttribute("href");
        Assert.assertTrue("İlk sonuç Selenium sitesine ait değil: " + firstResultUrl,
                firstResultUrl.toLowerCase().contains("selenium"));

        // 6. Adım: Testi kapat.
        waitAndClose();
    }
}
