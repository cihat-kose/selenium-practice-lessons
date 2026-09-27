package _12_RobotClass;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.BaseDriver;

import java.nio.file.Paths;

/**
 * Robot ile native dosya penceresi kullanımı için karşılaştırma örneği.
 * WebDriver sendKeys yaklaşımı HTML file input'una tam dosya yolunu verir;
 * masaüstü dosya penceresini açmaz ve tarayıcı otomasyonunda genellikle daha kararlıdır.
 */
public class FileUploadWithWebDriverLetcode extends BaseDriver {

    @Test
    public void uploadFileUsingWebDriver() {
        driver.get("https://letcode.in/file");

        String filePath = Paths.get("src", "test", "resources", "upload-sample.txt")
                .toAbsolutePath().toString();
        // Selenium, yerel dosya yolunu doğrudan input[type=file] alanına gönderir.
        WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
        fileInput.sendKeys(filePath);

        Assert.assertTrue("Dosya seçilmedi", fileInput.getAttribute("value").contains("upload-sample.txt"));
        waitAndClose();
    }
}
