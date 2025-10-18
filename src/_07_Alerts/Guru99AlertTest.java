package _07_Alerts;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utility.BaseDriver;
import utility.MyFunction;

public class Guru99AlertTest extends BaseDriver {

    /**
    Task:
     Basit Sağ Tıklama ve Çift Tıklama İşlemleriyle Alert Penceresi Yönetimi

    Görev Adımları:
    1. "http://demo.guru99.com/test/simple_context_menu.html" adresine gidin.
    2. Sayfadaki sağ tıklanabilir butona sağ tıklayın ve açılan menüden "Copy" seçeneğine tıklayın.
    3. Sağ tıklama sonrasında bir alert penceresi açılacak. Bu alert penceresini kabul edin (OK butonuna basın).
    4. Aynı sayfadaki "Double Click Me" butonuna çift tıklayın.
    5. Çift tıklama sonrası açılacak alert penceresini doğrulayın ve kapatın (OK butonuna basın).
    6. Testi sonlandırın ve tarayıcıyı kapatın.
     */

    @Test
    public void handleAlerts() {
        // 1. Adım: Web sitesine git
        driver.get("http://demo.guru99.com/test/simple_context_menu.html");

        // Sayfanın tamamen yüklendiğiniN gözlemleyebilmesi için kısa bir bekleme eklen.
        MyFunction.wait(2);

        // 2. Adım: Sağ tıklanacak butonu bul ve sağ tıkla
        WebElement rightClickButton = driver.findElement(By.cssSelector(".context-menu-one"));

        // Sağ tıklama işlemi gerçekleştirilir
        Actions actions = new Actions(driver);
        actions.contextClick(rightClickButton).perform();  // Sağ tıklama işlemi

        // Sağ tıklama işleminin ardından açılan menünün görüntülenebilmesi için kısa bir bekleme.
        MyFunction.wait(2);

        // 3. Adım: Açılan menüden "Copy" seçeneğine tıkla
        WebElement copyOption = driver.findElement(By.cssSelector(".context-menu-icon-copy"));
        copyOption.click();

        // "Copy" seçeneğine tıklandıktan sonra bu işlemin sonuçlarının gözlemlenebilmesi için kısa bir duraklama.
        MyFunction.wait(2);

        // 4. Adım: Sağ tıklama sonrası açılan alert penceresini yakala ve kapat (OK butonuna bas)
        String alertMessage = driver.switchTo().alert().getText();  // Alert mesajını al
        System.out.println("Sağ tıklama sonrası açılan alert mesajı: " + alertMessage);  // Konsola yazdır

        // 4.1 Adım: Alert mesajını doğrula (JUnit Assertion)
        String expectedRightClickMessage = "clicked: copy";  // Burada beklenen mesaj güncelleniyor
        Assert.assertEquals("Sağ tıklama sonrası mesaj hatalı!", expectedRightClickMessage, alertMessage);  // Assertion

        // Alert penceresini kapatma işlemi gerçekleştirilir
        driver.switchTo().alert().accept();  // Alert'i kapat (OK butonu)

        // Alert penceresinin kapanma işlemi gözlemlenebilmesi için kısa bir bekleme eklenir.
        MyFunction.wait(2);

        // 5. Adım: Çift tıklama yapılacak butonu bul ve çift tıkla
        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
        actions.doubleClick(doubleClickButton).perform();  // Çift tıklama işlemi

        // Çift tıklama işlemi sonrasında oluşan durumun gözlemlenebilmesi için kısa bir bekleme eklenir.
        MyFunction.wait(2);

        // 6. Adım: Çift tıklama sonrası açılan alert penceresini yakala ve doğrula
        String doubleClickAlertMessage = driver.switchTo().alert().getText();  // Alert mesajını al
        System.out.println("Çift tıklama sonrası alert mesajı: " + doubleClickAlertMessage);

        // Beklenen mesajı doğrula (JUnit Assertion)
        String expectedDoubleClickMessage = "You double clicked me.. Thank You..";
        Assert.assertEquals("Çift tıklama sonrası mesaj hatalı!", expectedDoubleClickMessage, doubleClickAlertMessage);  // Assertion

        // Alert penceresinin kapanması için OK butonuna basılır
        driver.switchTo().alert().accept();  // Alert'i kapat (OK butonu)

        // Alert'in kapanması gözlemlenebilmesi için kısa bir bekleme daha eklenir.
        MyFunction.wait(2);

        // 7. Adım: Testi sonlandır ve tarayıcıyı kapat
        waitAndClose();
    }
}
