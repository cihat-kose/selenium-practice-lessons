package _07_Alerts;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

public class TheInternetHerokuappAlertExamples extends BaseDriver {
    private static final String SELENIUM_ALERTS_PAGE = "https://www.selenium.dev/selenium/web/alerts.html";

    @Test
    public void handleSimpleJSAlertTest() {
        // Selenium'un örnek sayfasındaki düğme "cheese" metinli browser alert açar.
        driver.get(SELENIUM_ALERTS_PAGE);
        driver.findElement(By.id("alert")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("cheese", alert.getText());
        alert.accept();

        waitAndClose();
    }

    @Test
    public void handleJSConfirmAlertTest() {
        // Confirm alert mesajını kontrol et ve Cancel anlamındaki dismiss() ile kapat.
        driver.get(SELENIUM_ALERTS_PAGE);
        driver.findElement(By.id("confirm")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Are you sure?", alert.getText());
        alert.dismiss();

        waitAndClose();
    }

    @Test
    public void handleJSPromptAlertTest() {
        // Prompt alert'e metin yaz; sayfa yazılan metni sonuç alanında gösterir.
        driver.get(SELENIUM_ALERTS_PAGE);
        driver.findElement(By.id("prompt")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Enter something", alert.getText());

        String answer = "Merhaba, Selenium";
        alert.sendKeys(answer);
        alert.accept();

        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("text")));
        Assert.assertEquals(answer, result.getText());

        waitAndClose();
    }

    @Test
    public void handleRightClickAlertTest() {
        // Sağ tıklama örneği The Internet demo sayfasında kalır.
        driver.get("https://the-internet.herokuapp.com/context_menu");
        WebElement elementToRightClick = driver.findElement(By.id("hot-spot"));

        new Actions(driver).contextClick(elementToRightClick).perform();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("You selected a context menu", alert.getText());
        alert.accept();

        waitAndClose();
    }
}
