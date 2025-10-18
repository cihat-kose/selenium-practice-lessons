package _07_Alerts;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

public class DemoQAAlertWaitExample extends BaseDriver {
    /**
     Task: Explicit Wait ile Zamanlı Alert Yakalama
     Görev Adımları:

     1. "https://demoqa.com/alerts" sitesine gidiniz.
     2. "Click me" yazan butona tıklayınız. Bu buton, 5 saniye sonra bir alert penceresi açacaktır.
     3. Bu alert penceresinin açılmasını bekleyiniz.
     4. Alert penceresi açıldıktan sonra, alert'i kabul ediniz (OK butonuna basınız).
     */

    @Test
    public void waitForAlert() {
        // 1. Adım: DemoQA Alert sayfasına git
        driver.get("https://demoqa.com/alerts");

        // 2. Adım: Zamanlayıcı ile alert çıkaran butonu bul ve tıkla
        WebElement timerAlertButton = driver.findElement(By.id("timerAlertButton"));
        timerAlertButton.click();

        // 3. Adım: Explicit Wait ile alert penceresinin açılmasını bekle
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Maksimum 10 saniye bekle
        wait.until(ExpectedConditions.alertIsPresent()); // Alert'in görünmesini bekle

        // 4. Adım: Alert penceresini kabul et (OK butonuna bas)
        driver.switchTo().alert().accept();

        // Testin başarılı olduğundan emin ol, tarayıcıyı kapat
        waitAndClose();
    }
}
