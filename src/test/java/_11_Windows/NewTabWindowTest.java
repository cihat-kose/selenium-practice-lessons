package _11_Windows;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class NewTabWindowTest extends BaseDriver {

    @Test
    public void newTabWindowTest() {
        driver.get("https://demoqa.com/browser-windows");

        List<WebElement> consent = driver.findElements(By.xpath("//p[@class='fc-button-label']"));
        if (!consent.isEmpty()) {
            consent.get(0).click();
        }

        String currentWindowHandle = driver.getWindowHandle();
        driver.findElement(By.id("tabButton")).click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> windowIDs = driver.getWindowHandles();
        for (String windowID : windowIDs) {
            if (!windowID.equals(currentWindowHandle)) {
                driver.switchTo().window(windowID);
                break;
            }
        }

        WebElement newTabText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("sampleHeading")));
        assertEquals("This is a sample page", newTabText.getText());
        System.out.println("Yeni sekmedeki metin: " + newTabText.getText());

        waitAndClose();
    }
}
