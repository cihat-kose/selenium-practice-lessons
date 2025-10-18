package _07_Alerts;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utility.BaseDriver;

public class TheInternetHerokuappAlertExamples extends BaseDriver {

    /**
     Task 1:
     JavaScript Alert

     Görev Adımları:
     1. "https://the-internet.herokuapp.com/" sitesine gidiniz.
     2. "JavaScript Alerts" linkine tıklayınız.
     3. "Click for JS Alert" butonuna tıklayınız.
     4. Açılan alert penceresindeki mesajı alıp konsola yazdırınız.
     5. Alert'i kabul ediniz.
     6. Testi sonlandırınız.
     */
    @Test
    public void handleSimpleJSAlertTest() {
        // 1. Adım: Web sitesine git
        driver.get("https://the-internet.herokuapp.com/");

        // 2. Adım: "JavaScript Alerts" linkine tıkla
        WebElement jsAlertLink = driver.findElement(By.xpath("//*[text()='JavaScript Alerts']"));
        jsAlertLink.click();

        // 3. Adım: "Click for JS Alert" butonuna tıkla
        WebElement alertButton1 = driver.findElement(By.xpath("(//button)[1]"));
        alertButton1.click();

        // 4. Adım: Alert'e geç ve mesajı al
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert Mesajı: " + alertText);

        // 5. Adım: Alert penceresini kabul et
        alert.accept();

        // 6. Adım: Testi sonlandır
        waitAndClose();
    }

    /**
     Task 2:
     JavaScript Confirm Alert

     Görev Adımları:
     1. "https://the-internet.herokuapp.com/" sitesine gidiniz.
     2. "JavaScript Alerts" linkine tıklayınız.
     3. "Click for JS Confirm" butonuna tıklayınız.
     4. Açılan alert penceresindeki mesajı alıp konsola yazdırınız.
     5. Alert penceresinde Cancel butonuna basınız.
     6. Testi sonlandırınız.
     */
    @Test
    public void handleJSConfirmAlertTest() {
        // 1. Adım: Web sitesine git
        driver.get("https://the-internet.herokuapp.com/");

        // 2. Adım: "JavaScript Alerts" linkine tıkla
        WebElement jsAlertLink = driver.findElement(By.xpath("//*[text()='JavaScript Alerts']"));
        jsAlertLink.click();

        // 3. Adım: "Click for JS Confirm" butonuna tıkla
        WebElement alertButton2 = driver.findElement(By.xpath("(//button)[2]"));
        alertButton2.click();

        // 4. Adım: Alert'e geç ve mesajı al
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert Mesajı: " + alertText);

        // 5. Adım: Alert penceresinde Cancel butonuna bas
        alert.dismiss();

        // 6. Adım: Testi sonlandır
        waitAndClose();
    }

    /**
     Task 3:
     JavaScript Prompt Alert

     Görev Adımları:
     1. "https://the-internet.herokuapp.com/" sitesine gidiniz.
     2. "JavaScript Alerts" linkine tıklayınız.
     3. "Click for JS Prompt" butonuna tıklayınız.
     4. Açılan alert penceresindeki mesajı alıp konsola yazdırınız.
     5. Alert penceresine "Merhaba, Selenium" yazınız ve OK butonuna basınız.
     6. Sonuç mesajını alıp konsola yazdırınız.
     7. Testi sonlandırınız.
     */
    @Test
    public void handleJSPromptAlertTest() {
        // 1. Adım: Web sitesine git
        driver.get("https://the-internet.herokuapp.com/");

        // 2. Adım: "JavaScript Alerts" linkine tıkla
        WebElement jsAlertLink = driver.findElement(By.xpath("//*[text()='JavaScript Alerts']"));
        jsAlertLink.click();

        // 3. Adım: "Click for JS Prompt" butonuna tıkla
        WebElement alertButton3 = driver.findElement(By.xpath("(//button)[3]"));
        alertButton3.click();

        // 4. Adım: Alert'e geç ve mesajı al
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert Mesajı: " + alert.getText());

        // 5. Adım: Alert'e metin gönder ve kabul et
        alert.sendKeys("Merhaba, Selenium");
        alert.accept();

        // 6. Adım: Sonuç mesajını al ve konsola yazdır
        WebElement resultText = driver.findElement(By.id("result"));
        System.out.println("Alert'e gönderilen mesaj: " + resultText.getText());

        // 7. Adım: Testi sonlandır
        waitAndClose();
    }

    /**
     Task 4:
     Sağ Tıklama (Right Click) ve Alert

     Görev Adımları:
     1. "http://the-internet.herokuapp.com/context_menu" sitesine gidiniz.
     2. "Hot Spot" elementine sağ tıklayınız.
     3. Açılan alert penceresindeki mesajı alıp konsola yazdırınız.
     4. Alert penceresini kabul ediniz.
     5. Testi sonlandırınız.
     */
    @Test
    public void handleRightClickAlertTest() {
        // 1. Adım: Web sitesine git
        driver.get("http://the-internet.herokuapp.com/context_menu");

        // 2. Adım: Sağ tıklanacak elementi bul
        WebElement elementToRightClick = driver.findElement(By.id("hot-spot"));

        // 3. Adım: Sağ tıklama işlemi yap
        Actions actions = new Actions(driver);
        actions.contextClick(elementToRightClick).perform();  // Sağ tıklama işlemi

        // 4. Adım: Açılan alert penceresindeki mesajı al
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert içeriği: " + alert.getText());

        // 5. Adım: Alert penceresini kabul et
        alert.accept();

        // 6. Adım: Testi sonlandır
        waitAndClose();
    }
}
