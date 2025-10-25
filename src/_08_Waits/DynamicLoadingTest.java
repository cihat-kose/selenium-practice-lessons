package _08_Waits;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

public class DynamicLoadingTest extends BaseDriver {

    /**
     Task 3:
     Explicit Wait (Button Click)

     Görev:
     - "https://the-internet.herokuapp.com/dynamic_loading/2" sitesine gidiniz.
     - Start butonuna tıklayınız.
     - Dinamik olarak yüklenecek "Hello World!" yazısını bekleyiniz ve yazının görünür olduğunu doğrulayınız.

     Çözüm:
     Bu task'ta, Explicit Wait kullanarak dinamik olarak yüklenen bir elementin görünür olmasını bekleyeceğiz.
     */
    @Test
    public void explicitWaitButtonTask() {
        // 1. Adım: Web sitesine git
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");

        // 2. Adım: "Start" butonunu bulun ve tıklayın
        WebElement startButton = driver.findElement(By.xpath("//button[text()='Start']"));
        startButton.click();

        // 3. Adım: "Hello World!" yazısının görünmesini bekleyin (Explicit Wait ile)
        WebElement helloWorldText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Hello World!']")));

        // 4. Adım: Yazının ekrana geldiğini konsola yazdır
        System.out.println("helloWorldText.getText() = " + helloWorldText.getText());

        // 5. Adım: Yazının gerçekten göründüğünü doğrula
        Assert.assertTrue("'Hello World!' yazısı görünmedi!", helloWorldText.isDisplayed());

        // 6. Adım: Test bittikten sonra tarayıcıyı kapat
        waitAndClose();
    }
}
