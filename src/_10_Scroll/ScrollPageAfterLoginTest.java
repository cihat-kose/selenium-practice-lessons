package _10_Scroll;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;
import utility.MyFunction;

public class ScrollPageAfterLoginTest extends BaseDriver {

    /**
     Task:

     - Web sayfasına gidin.
     - Giriş formuna Kullanıcı Adı: Admin, Şifre: admin123 bilgilerini girin.
     - Giriş yaptıktan sonra, sayfanın üst kısmındaki navigasyon çubuğunun görünmesini bekleyin.
     - Sayfanın en altına kaydırın.
     - 3 saniye bekleyin ve ardından sayfanın en üstüne geri kaydırın.
     */
    @Test
    public void scrollPageAfterLogin() {
        // 1. Adım: Web sitesine gidin
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // 2. Adım: Giriş formundaki elemanları locate edin
        WebElement user = driver.findElement(By.cssSelector("[name='username']"));
        WebElement pass = driver.findElement(By.cssSelector("[name='password']"));
        WebElement login = driver.findElement(By.cssSelector("[type='submit']"));

        // 3. Adım: Kullanıcı adı ve şifre girin
        user.sendKeys("Admin");
        pass.sendKeys("admin123");

        // 4. Adım: Login butonuna tıklayın
        login.click();

        // 5. Adım: Giriş yaptıktan sonra navigasyon çubuğunun (top bar) görünmesini bekleyin
        WebElement topBar = driver.findElement(By.cssSelector("[class='oxd-topbar']"));
        wait.until(ExpectedConditions.visibilityOf(topBar));

        MyFunction.wait(3); // Hareketi gözlemlemek için kısa bekleme

        // 6. Adım: Sayfanın en altına kaydırın
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // Sayfanın en altına kaydırma

        // 7. Adım: 3 saniye bekleyin
        MyFunction.wait(3); // Hareketi gözlemlemek için kısa bekleme

        // 8. Adım: Sayfanın en üstüne geri kaydırın
        js.executeScript("window.scrollTo(document.body.scrollHeight, 0)"); // Sayfanın en üstüne geri kaydırma

        // 9. Test bittikten sonra tarayıcıyı kapat
        waitAndClose();
    }
}
