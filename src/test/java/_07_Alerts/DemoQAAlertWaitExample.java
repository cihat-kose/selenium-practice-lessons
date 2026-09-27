package _07_Alerts;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

/** Selenium'un gecikmeli alert örneğiyle alertIsPresent beklemesini gösterir. */
public class DemoQAAlertWaitExample extends BaseDriver {

    @Test
    public void waitForAlert() {
        // Selenium'un slow-alert düğmesi alert'i kısa bir gecikmeyle açar.
        driver.get("https://www.selenium.dev/selenium/web/alerts.html");
        driver.findElement(By.id("slow-alert")).click();

        // Alert hemen açılmadığı için, açılana kadar koşullu bekleme yap.
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Slow", alert.getText());
        alert.accept();

        waitAndClose();
    }
}
