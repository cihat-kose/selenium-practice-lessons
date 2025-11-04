package _12_RobotClass;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.BaseDriver;
import utility.MyFunction;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * Bu test, https://letcode.in/file sitesinde
 * Java Robot sınıfını kullanarak dosya yükleme otomasyonunu gösterir.
 * Dosya seçme penceresi, klavye girişleri (TAB, ENTER, CTRL+V, ENTER)
 * simüle edilerek yönetilir.
 */

public class FileUploadWithRobotLetcode extends BaseDriver {

    @Test
    public void uploadFileToLetcodeTest() throws AWTException {
        driver.get("https://letcode.in/file");
        MyFunction.wait(1);

        Robot robot = new Robot();

        // "Choose File" (Dosya Seç) butonuna odaklanmak için TAB tuşuna 6 kez bas
        for (int i = 0; i < 6; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        }

        // ENTER tuşuna basarak dosya seçme penceresini aç
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        MyFunction.wait(1);

        // Dosya yolunu panoya kopyala (öğrenciler: kendi yolunuzu yazın)
        StringSelection filePath = new StringSelection("C:\\Users\\cihat\\Desktop\\filePath.txt");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);

        // CTRL+V ile dosya yolunu yapıştır
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // ENTER tuşuna basarak dosya seçimini onayla
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Dosyanın seçildiğini doğrula
        WebElement fileSelectedMessage = driver.findElement(By.cssSelector("[class='file']+p"));
        Assert.assertTrue("Dosya yükleme başarısız", fileSelectedMessage.getText().contains("filePath.txt"));

        waitAndClose();
    }
}
