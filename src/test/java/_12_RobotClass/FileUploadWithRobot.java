package _12_RobotClass;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;
import utility.MyFunction;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.nio.file.Paths;

/**
 * Bu test, Java Robot sınıfını kullanarak bir dosyanın nasıl yükleneceğini gösterir.
 * TAB, ENTER ve CTRL+V gibi klavye eylemlerini simüle ederek
 * dosya yükleme penceresi ve form öğeleriyle etkileşir.
 */

public class FileUploadWithRobot extends BaseDriver {

    @Test
    public void uploadFileUsingRobotTest() throws AWTException {
        driver.get("http://demo.guru99.com/test/upload/");
        // Sayfa açıldıktan sonra çerez penceresi gibi dinamik öğelerin yerleşmesine zaman tanı.
        MyFunction.wait(1);

        // Çerez/onay penceresi çıkarsa kabul et
        List<WebElement> acceptAllFrame = driver.findElements(By.id("gdpr-consent-notice"));
        if (!acceptAllFrame.isEmpty()) {
            driver.switchTo().frame(acceptAllFrame.get(0));

            List<WebElement> acceptAll =
                    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.xpath("//span[text()='Accept All']")));

            if (!acceptAll.isEmpty())
                acceptAll.get(0).click();

            // Selenium varsayılan sayfaya kendiliğinden dönmez; dosya alanı ana sayfadadır.
            driver.switchTo().defaultContent();
        }

        // Bu sayfa gerçek file input'u gizlediği için native düğmeye TAB sırasıyla odaklan.
        // WebDriver sendKeys alternatifi, FileUploadWithWebDriverLetcode sınıfında gösteriliyor.
        Robot robot = new Robot();
        robot.setAutoDelay(100);
        for (int i = 0; i < 15; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Native dosya penceresinin açılması Selenium tarafından izlenemediği için kısa bekle.
        MyFunction.wait(1);

        // Projedeki ortak test dosyasının tam yolunu bulup panoya kopyala.
        // Bu fixture depoda bulunduğundan öğrencilerin kendi bilgisayarlarına göre yol değiştirmesi gerekmez.
        StringSelection filePath = new StringSelection(
                Paths.get("src", "test", "resources", "upload-sample.txt").toAbsolutePath().toString());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);

        // Panoya yapıştırılan yolun dosya adı alanına gelmesini bekle.
        MyFunction.wait(1);

        // CTRL+V ile dosya yolunu yapıştır
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        // Dosya penceresi kapanıp seçilen dosya sayfadaki input'a yansısın.
        MyFunction.wait(1);

        for (int i = 0; i < 2; i++) {
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }

        MyFunction.wait(1);

        for (int i = 0; i < 2; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        }

        WebElement termsCheckbox = driver.findElement(By.id("terms"));
        if (!termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }

        // Robot ile klavye odağını ilerletme adımını göster; submit işlemini locator ile yap.
        for (int i = 0; i < 2; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        }

        // Formu tamamlamak için Submit File düğmesine bas.
        WebElement submitButton = driver.findElement(By.id("submitbutton"));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();

        // Submit işleminden sonra başarı mesajı center öğesinde görünür.
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//center[contains(normalize-space(.), 'has been successfully uploaded.')]")));
        Assert.assertTrue("Dosya yükleme başarısız", successMessage.isDisplayed());

        // Başarı mesajı görünür olduktan sonra öğrenci sonucu inceleyebilsin diye
        // tarayıcıyı kısa süre açık tutar; ardından kapatır.
        waitAndClose();
    }
}
