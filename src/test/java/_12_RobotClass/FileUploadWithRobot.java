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

/**
 * Bu test, Java Robot sınıfını kullanarak bir dosyanın nasıl yükleneceğini gösterir.
 * TAB, ENTER, CTRL+V ve SPACE gibi klavye eylemlerini simüle ederek
 * dosya yükleme penceresi ve form öğeleriyle etkileşir.
 */

public class FileUploadWithRobot extends BaseDriver {

    @Test
    public void uploadFileUsingRobotTest() throws AWTException {
        driver.get("http://demo.guru99.com/test/upload/");
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
        }

        // Robot sınıfı ile dosya yükleme işlemini simüle et
        Robot robot = new Robot();

        // "Choose File" (Dosya Seç) butonuna ulaşmak için TAB tuşuna 15 kez bas
        for (int i = 0; i < 15; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        }

        // ENTER’a basarak dosya seçme penceresini aç
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Dosya yolunu panoya (clipboard) kopyala
        // (öğrenciler kendi sistemlerine göre bu yolu değiştirmeli)
        StringSelection filePath = new StringSelection("C:\\Users\\cihat\\Desktop\\filePath.txt");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);

        MyFunction.wait(1);

        // CTRL+V ile dosya yolunu yapıştır
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        MyFunction.wait(1);

        // ENTER’a basarak dosya seçimini onayla
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        MyFunction.wait(3);

        // Onay kutusuna ulaşmak için TAB tuşuna 2 kez bas
        for (int i = 0; i < 2; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        }

        MyFunction.wait(1);

        // Şartlar kutusunu SPACE tuşuyla işaretle
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_SPACE);

        MyFunction.wait(1);

        // Gönderme butonuna ulaşmak için TAB tuşuna 2 kez bas
        for (int i = 0; i < 2; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        }

        MyFunction.wait(1);

        // ENTER’a basarak formu gönder
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Başarı mesajının göründüğünü doğrula
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[text()='has been successfully uploaded.']")));

        Assert.assertTrue(message.isDisplayed());

        waitAndClose();
    }
}
