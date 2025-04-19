package _10_Scroll;

import org.junit.Test;
import org.openqa.selenium.By;
import utility.BaseDriver;

import java.util.ArrayList;
import java.util.List;

public class InfiniteScrollTest extends BaseDriver {

    /**
     Task:
     - https://the-internet.herokuapp.com/infinite_scroll adresine gidin.
     - Sayfayı aşağıya kaydırarak 10 paragrafın yüklenmesini sağlayın.
     - Yüklenen 10 paragrafı konsola yazdırın.

     Adımlar:
     1. Sayfaya gidin ve sayfanın tamamen yüklendiğinden emin olun.
     2. JavaScriptExecutor kullanarak sayfanın altına doğru kaydırma işlemi yapın.
     3. Kaydırma sonrası, yeni yüklenen paragrafları tespit edin.
     4. Toplamda 10 paragraf yüklendikten sonra, bu paragrafları konsola yazdırın.
     5. Test sonunda tarayıcıyı kapatın.
     */

    @Test
    public void loadAndPrintTenParagraphs() {
        driver.get("https://the-internet.herokuapp.com/infinite_scroll");

        // 2. Adım: Paragrafları saklamak için bir liste oluşturun
        List<String>paragraphs = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            // 4. Adım: JavaScriptExecutor ile sayfayı aşağı kaydırın
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        }



    }
}
