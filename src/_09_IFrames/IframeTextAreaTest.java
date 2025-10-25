package _09_IFrames;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

import java.util.List;

public class IframeTextAreaTest extends BaseDriver {

    /**
     Task:

     - "https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_textarea" adresine gidin.
     - Çerez onayı varsa kapatın.
     - Sayfadaki iframe'e geçiş yapın.
     - Iframe içindeki textarea'nın mevcut metnini temizleyip, yeni metin yazın: "Bu metin Selenium ile değiştirilmiştir!"
     - Iframe'den çıkın ve ana sayfaya dönün.
     - Birkaç saniye bekleyip sayfayı kapatın.
     */

    @Test
    public void iframeTextAreaTest() {
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_textarea");

        List<WebElement> acceptButton = driver.findElements(By.id("accept-choices"));
        if( !acceptButton.isEmpty()){
            acceptButton.get(0).click();
        }

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='iframeResult']")));

//        // Alternatif geçiş yöntemleri:

//        WebElement iframe = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
//        driver.switchTo().frame(iframe);

//        driver.switchTo().frame("iframeResult");
//        driver.switchTo().frame(0);

        WebElement textarea = driver.findElement(By.id("w3review"));
        textarea.clear();

        textarea.sendKeys("Bu metin Selenium ile değiştirilmiştir!");

        driver.switchTo().parentFrame();

        // Alternatif: Tüm iframelerden çıkıp doğrudan ana sayfaya dönmek için:
        // driver.switchTo().defaultContent();

        waitAndClose();
    }
}
