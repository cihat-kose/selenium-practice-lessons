package _11_Windows;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class NewTabWindowTest extends BaseDriver {
    private static final String WINDOW_PAGE =
            "https://www.selenium.dev/selenium/web/window_switching_tests/page_with_frame.html";

    @Test
    public void newTabWindowTest() {
        // Selenium'un örnek sayfasında "Open new window" bağlantısı yeni pencere açar.
        driver.get(WINDOW_PAGE);
        String originalWindow = driver.getWindowHandle();

        driver.findElement(By.linkText("Open new window")).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Yeni pencerenin kimliğini bulup odağı ona geçir.
        Set<String> handles = driver.getWindowHandles();
        String newWindow = handles.stream()
                .filter(handle -> !handle.equals(originalWindow))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Yeni pencere açılmadı."));
        driver.switchTo().window(newWindow);

        // Selenium'un örnek yeni penceresinin başlığını doğrula.
        wait.until(ExpectedConditions.titleIs("Simple Page"));
        assertEquals("Simple Page", driver.getTitle());

        // Yeni pencereyi kapat, ana pencereye dön ve tek pencere kaldığını doğrula.
        driver.close();
        driver.switchTo().window(originalWindow);
        wait.until(ExpectedConditions.numberOfWindowsToBe(1));
        assertEquals(1, driver.getWindowHandles().size());

        waitAndClose();
    }
}
