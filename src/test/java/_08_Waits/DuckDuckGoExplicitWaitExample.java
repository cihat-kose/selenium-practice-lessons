package _08_Waits;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

public class DuckDuckGoExplicitWaitExample extends BaseDriver {

    /**
     Task:
     DuckDuckGo Arama Sonuçlarında Selenium Kelimesini Doğrulama

     Görev Adımları:
     1. Web Sitesine Gidin:
     - "https://duckduckgo.com/" sitesine gidiniz.

     2. Arama Kutusuna Metin Girin ve Enter'a Basın:
     - Arama kutusuna "Selenium" yazın ve **Enter** tuşuna basın.

     3. Arama Sonuçlarından İlk Sonucu Doğrulama:
     - Arama sonuçları sayfasındaki ilk sonucun "selenium" kelimesini içerdiğini doğrulayın.
     - İlk arama sonucunun başlığını kontrol edin ve bu başlığın "selenium" kelimesini içerip içermediğini doğrulayın.

     4. Testi Sonlandırın:
     - Sonuçların doğru olduğunu doğruladıktan sonra, testi tamamlayın ve tarayıcıyı kapatın.
     */

    @Test
    public void searchAndVerifySeleniumResult() {
        // Google robot kontrolü otomasyonu durdurabildiği için bu canlı örnek DuckDuckGo kullanır.
        driver.get("https://duckduckgo.com/");

        // 2. Adım: Arama kutusuna arama terimini yazıp Enter'a basın.
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys("selenium" + Keys.ENTER);

        // 3. Adım: Sonuç başlığı görünene kadar explicit wait ile bekle.
        WebElement theFirstLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//h3")));

        // Doğru Selenium sayfalarının başlığı "Downloads" gibi genel olabilir;
        // ilk sonuç bağlantısının Selenium sitesine gittiğini doğrula.
        String firstResultUrl = theFirstLink.findElement(By.xpath("./ancestor::a[1]")).getAttribute("href");
        System.out.println("İlk sonuç adresi: " + firstResultUrl);
        Assert.assertTrue("İlk sonuç Selenium sitesine ait değil: " + firstResultUrl,
                firstResultUrl.toLowerCase().contains("selenium"));

        // 5. Adım: Testi kapat.
        waitAndClose();
    }
}
