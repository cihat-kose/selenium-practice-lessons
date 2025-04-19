package _12_RobotClass;

import org.junit.Test;
import utility.BaseDriver;

import java.awt.*;
import java.awt.event.KeyEvent;

public class NotepadAutomationTest extends BaseDriver {

    /**
        Task: Robot Class'ı kullanarak bir Not Defteri penceresine metin yazın.

        1. Not Defteri'ni manuel olarak açın (veya izin veriliyorsa Çalışma Zamanı'nı kullanarak otomatikleştirin).
        2. Pencerenin odaklanması için birkaç saniye bekleyin.
        3. Klavye simülasyonunu kullanarak "Robot Sınıfından Merhaba" yazın.
     */
    @Test
    public void writeTextInNotepad() throws Exception {
        // 1. Create Robot instance
        Robot robot = new Robot();
        robot.setAutoDelay(100);  // Delay between each keystroke

        // 2. İsteğe bağlı: Not Defteri'ni otomatik olarak başlat (Yalnızca Windows'ta çalışır)
        Runtime.getRuntime().exec("notepad");
        Thread.sleep(2000); // Wait for Notepad to open

        // 3. Metin yazma
        String message = "Hello from Robot Class";

        for (char ch : message.toCharArray()) {
            // Yalnızca desteklenen karakterleri işle
            if (Character.isLetter(ch)) {
                boolean isUpper = Character.isUpperCase(ch);
                if (isUpper) robot.keyPress(KeyEvent.VK_SHIFT);

                int keyCode = KeyEvent.getExtendedKeyCodeForChar(ch);
                robot.keyPress(keyCode);
                robot.keyRelease(keyCode);

                if (isUpper) robot.keyRelease(KeyEvent.VK_SHIFT);
            } else if (ch == ' ') {
                robot.keyPress(KeyEvent.VK_SPACE);
                robot.keyRelease(KeyEvent.VK_SPACE);
            }
            // '.' veya ',' gibi ek karakterler buraya bir anahtar büyük/küçük harf ile eklenebilir
        }
        waitAndClose();
    }
}
