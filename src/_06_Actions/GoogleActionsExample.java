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

import java.util.List;

public class GoogleActionsExample extends BaseDriver {

    /**
     * Task: Google Arama Sonuçlarında Selenium Kelimesini Doğrulama
     * Görev Adımları:
     * 1. Web Sitesine Gidin:
     * - "https://google.com/" sitesine gidiniz.
     * 2. Arama Kutusuna Metin Girin ve Enter'a Basın:
     * - Google'ın arama kutusuna "Selenium" yazın ve Enter tuşuna basın.
     * 3. Arama Sonuçlarından İlk Sonucu Doğrulama:
     * - Arama sonuçları sayfasındaki ilk sonucun "selenium" kelimesini içerdiğini doğrulayın.
     * - İlk arama sonucunun başlığını kontrol edin ve bu başlığın "selenium" kelimesini içerip içermediğini doğrulayın.
     * 4. Testi Sonlandırın:
     * - Sonuçların doğru olduğunu doğruladıktan sonra, testi tamamlayın ve tarayıcıyı kapatın.
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

        // 3. Adım: Arama kutusunu bulun
        WebElement searchInput = driver.findElement(By.xpath("//textarea[@id='APjFqb']"));

        // 4. Adım: Selenium kelimesini arama kutusuna yazın ve Enter tuşuna basın
        Actions actions = new Actions(driver);
        Action action = actions
                .moveToElement(searchInput)  // Arama kutusuna gel
                .click()  // Tıkla
                .sendKeys("selenium" + Keys.ENTER)  // Selenium yaz ve Enter'a bas
                .build();
        action.perform();  // Aksiyon zincirini gerçekleştir

        // 5. Adım: İlk arama sonucunu bul ve tıklanabilir olmasını bekle
        WebElement theFirstLink = driver.findElement(By.xpath("//h3[@class='LC20lb MBeuO DKV0Md']"));
        wait.until(ExpectedConditions.elementToBeClickable(theFirstLink)); // Tıklanabilir olmasını bekle

        // 6. Adım: İlk sonucun başlığını al ve "Selenium" kelimesini içerip içermediğini doğrula
        // Ilk yontem: Sonucun basliginin tam olarak "Selenium" olup olmadigini kontrol et
        Assert.assertEquals("Selenium", theFirstLink.getText());

        // Ikinci yontem: Basligin icinde "selenium" kelimesinin gecip gecmedigini kontrol et
        Assert.assertTrue(theFirstLink.getText().toLowerCase().contains("selenium"));

        // Testi sonlandir
        waitAndClose();
    }
}

