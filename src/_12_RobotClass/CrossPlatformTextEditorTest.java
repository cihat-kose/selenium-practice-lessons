package _12_RobotClass;

import org.junit.Test;
import utility.MyFunction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class CrossPlatformTextEditorTest {

    /**
     * Görev: Robot Class kullanarak sistemdeki not uygulamasına "Hello from Robot Class" yazdırın.
     *
     * Adımlar:
     * 1. İşletim sistemini tespit edin (Windows mu macOS mu?).
     * 2. İşletim sistemine göre uygun not uygulamasını açın:
     *    - Windows: Notepad
     *    - macOS: TextEdit
     * 3. Uygulamanın açılması için birkaç saniye bekleyin.
     * 4. Robot Class kullanarak "Hello from Robot Class" ifadesini klavye simülasyonu ile yazdırın.
     * 5. Yazma işlemi tamamlandıktan sonra test sonlansın.
     */

    @Test
    public void writeInSystemEditorAndClose() throws AWTException, IOException, InterruptedException {
        // 1. İşletim sistemini tespit et
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println("İşletim Sistemi: " + os);

        // 2. Not uygulamasını başlat
        if (os.contains("win")) {
            Runtime.getRuntime().exec("notepad");
        } else if (os.contains("mac")) {
            Runtime.getRuntime().exec("open -a TextEdit");
        } else {
            System.out.println("Bu işletim sistemi desteklenmiyor.");
            return;
        }

        // 3. Açılması için bekle
        MyFunction.wait(3);

        // 4. Robot nesnesi oluştur
        Robot robot = new Robot();
        robot.setAutoDelay(100);

        // 5. Yazılacak metin
        String message = "Hello from Robot Class";

        for (char ch : message.toCharArray()) {
            if (Character.isUpperCase(ch)) robot.keyPress(KeyEvent.VK_SHIFT);

            int keyCode = KeyEvent.getExtendedKeyCodeForChar(ch);
            if (KeyEvent.CHAR_UNDEFINED != keyCode) {
                robot.keyPress(keyCode);
                robot.keyRelease(keyCode);
            }

            if (Character.isUpperCase(ch)) robot.keyRelease(KeyEvent.VK_SHIFT);

            if (ch == ' ') {
                robot.keyPress(KeyEvent.VK_SPACE);
                robot.keyRelease(KeyEvent.VK_SPACE);
            }
        }

        // 6. Bekleme
        MyFunction.wait(1);

        // 7. Uygulamayı kapat
        if (os.contains("win")) {
            // ALT + F4 (Windows'ta kapatmak için)
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_F4);
            robot.keyRelease(KeyEvent.VK_F4);
            robot.keyRelease(KeyEvent.VK_ALT);
        } else if (os.contains("mac")) {
            // CMD + Q (macOS'ta kapatmak için)
            robot.keyPress(KeyEvent.VK_META); // CMD tuşu
            robot.keyPress(KeyEvent.VK_Q);
            robot.keyRelease(KeyEvent.VK_Q);
            robot.keyRelease(KeyEvent.VK_META);
        }
    }
}
