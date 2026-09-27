package _10_Scroll;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.BaseDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class InfiniteScrollTest extends BaseDriver {

    /**
     Task:
     - https://the-internet.herokuapp.com/infinite_scroll adresine gidin.
     - Sayfayı aşağıya kaydırarak 10 paragrafın yüklenmesini sağlayın.
     - Yüklenen 10 paragrafı konsola yazdırın.
     */

    @Test
    public void loadAndPrintTenParagraphs() {
        driver.get("https://the-internet.herokuapp.com/infinite_scroll");

        List<String> paragraphs = new ArrayList<>();
        By paragraphsLocator = By.cssSelector("div.jscroll-added");

        for (int i = 1; i <= 10; i++) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            int expectedCount = i;
            wait.until(d -> d.findElements(paragraphsLocator).size() >= expectedCount);

            WebElement paragraph = driver.findElements(paragraphsLocator).get(i - 1);
            assertFalse("Yüklenen paragraf metni boş.", paragraph.getText().trim().isEmpty());
            paragraphs.add(paragraph.getText());
            System.out.println(i + ". Paragraph: " + paragraph.getText());
        }

        assertEquals("Tam olarak 10 paragraf yüklenmeli.", 10, paragraphs.size());
        waitAndClose();
    }
}
