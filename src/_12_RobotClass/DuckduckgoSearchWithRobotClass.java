package _12_RobotClass;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;
import utility.MyFunction;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;


public class DuckduckgoSearchWithRobotClass extends BaseDriver {

    /**
        Task:

        1. DuckDuckGo ana sayfasına gidin: https://duckduckgo.com/
        2. Arama kutusuna tıklayarak odaklanın.
        3. Robot Class kullanarak "Selenium Robot Class" metnini panoya kopyalayın ve arama kutusuna yapıştırın.
        4. Arama işlemini başlatmak için "Enter" tuşuna basın.
        5. Arama sonuçlarında, sayfanın tam olarak yüklenmesini bekleyin (ilk sonuç başlığının görünmesini bekleyin).
        6. Fareyi ekranın sağ üst köşesine hareket ettirin ve sol tıklama yapın.
           Not: Koordinatlar ekranın çözünürlüğüne göre dinamik olarak ayarlanmış olmalıdır.
        7. Fareyi ekranın sol alt köşesine hareket ettirin ve sağ tıklama yapın.
           Not: Bu hareket de dinamik olarak ekran çözünürlüğüne göre ayarlanmış olmalıdır.
        8. Tüm işlemler tamamlandıktan sonra tarayıcıyı kapatın.
     */

    @Test
    public void searchDuckduckgo() throws AWTException {

        // 1. DuckDuckGo ana sayfasına git
        driver.get("https://duckduckgo.com");

        // 2. Arama kutusuna tıkla
        WebElement searchBox = driver.findElement(By.id("searchbox_input"));
        searchBox.click();

        // 3. Robot nesnesini oluştur
        Robot robot = new Robot();

        // 4. Ekran çözünürlüğünü al (fare hareketleri için dinamik kullanım)
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // 5. Aranacak metni panoya kopyala
        String searchTerm = "Selenium Robot Class";
        StringSelection stringSelection = new StringSelection(searchTerm);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        MyFunction.wait(2); // Kısa bekleme (gözlem için)

        // 6. Ctrl + V ile arama kutusuna yapıştır
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        MyFunction.wait(1);

        // 7. Enter tuşuna basarak aramayı başlat
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // 8. İlk sonuç başlığını bekle (sayfanın yüklendiğini gösterir)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//h3")));

        // 9. Fareyi ekranın sağ üst köşesine taşı (örneğin bildirim çubuğuna yakın bir bölge)
        robot.mouseMove(screenWidth - 10, 50);
        MyFunction.wait(2);

        // 10. Sol tıklama yap
        robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
        MyFunction.wait(2);

        // 11. Fareyi ekranın sol alt köşesine taşı
        robot.mouseMove(10, screenHeight - 50);
        MyFunction.wait(2);

        // 12. Sağ tıklama yap
        robot.mousePress(KeyEvent.BUTTON3_DOWN_MASK);
        robot.mouseRelease(KeyEvent.BUTTON3_DOWN_MASK);

        // 13. Tarayıcıyı kapat
        waitAndClose();
    }
}
