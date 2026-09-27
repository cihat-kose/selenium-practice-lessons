package _09_IFrames;

import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

import java.nio.file.Paths;

public class IframeTextAreaTest extends BaseDriver {

    /**
     * Task:
     * <p>
     * - Projedeki iframe-textarea.html örneğini açın.
     * - Sayfadaki iframe'e geçiş yapın.
     * - Iframe içindeki textarea'nın mevcut metnini temizleyip, yeni metin yazın: "Bu metin Selenium ile değiştirilmiştir!"
     * - Iframe'den çıkın ve ana sayfaya dönün.
     * - Birkaç saniye bekleyip sayfayı kapatın.
     */

    @Test
    public void iframeTextAreaTest() {
        // Harici TryIt sayfası reklam katmanı gösterebildiğinden aynı örneği depodaki HTML'den aç.
        String demoPage = Paths.get("src", "test", "resources", "iframe-textarea.html")
                .toAbsolutePath().toUri().toString();
        driver.get(demoPage);

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("textarea-frame")));

//        // Alternatif geçiş yöntemleri:

//        WebElement iframe = driver.findElement(By.id("textarea-frame"));
//        driver.switchTo().frame(iframe);

//        driver.switchTo().frame("textarea-frame");
//        driver.switchTo().frame(0);

        WebElement textarea = wait.until(ExpectedConditions.elementToBeClickable(By.id("review")));
        // Klavyeden CTRL+A ile seçmek, bazı tarayıcıların textarea.clear() davranışından daha uyumludur.
        textarea.click();
        textarea.sendKeys(Keys.chord(Keys.CONTROL, "a"), "Bu metin Selenium ile değiştirilmiştir!");
        Assert.assertEquals("Bu metin Selenium ile değiştirilmiştir!", textarea.getAttribute("value"));

        driver.switchTo().parentFrame();

        // Alternatif: Tüm iframelerden çıkıp doğrudan ana sayfaya dönmek için:
        // driver.switchTo().defaultContent();

        waitAndClose();
    }
}
