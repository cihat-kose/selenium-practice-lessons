package _12_RobotClass._02_CrossPlatform;

import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class CrossPlatformTextEditorTest {

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
        Thread.sleep(3000);

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
        Thread.sleep(1000);

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
