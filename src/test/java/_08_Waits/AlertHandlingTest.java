package _08_Waits;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

/**
 * Clicks Selenium's delayed-alert demo, waits for the alert, checks its message,
 * and accepts it.
 */
public class AlertHandlingTest extends BaseDriver {

    private static final String ALERTS_PAGE =
            "https://www.selenium.dev/selenium/web/alerts.html";

    @Test
    public void waitForAlertAndVerifyItsMessage() {
        driver.get(ALERTS_PAGE);

        // This link opens an alert after a short delay.
        driver.findElement(By.id("slow-alert")).click();

        // Wait until the browser reports that the alert is open.
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();

        // The Selenium demo's delayed alert displays the text "Slow".
        Assert.assertEquals("Slow", alert.getText());

        // Close the browser alert with its OK/Accept action.
        alert.accept();

        waitAndClose();
    }
}
