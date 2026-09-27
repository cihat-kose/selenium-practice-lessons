package _13_ShadowDom;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

import static org.junit.Assert.assertEquals;

/**
 * Açık bir Shadow DOM içindeki butona erişir ve tıklama sonucundaki metni doğrular.
 * Public site yerine repodaki yerel HTML fixture'ını kullanır.
 */
public class ShadowDomExampleTest extends BaseDriver {

    @Test
    public void clickButtonInsideOpenShadowRoot() {
        // Özel web component'i ve açık Shadow DOM'u içeren yerel örnek sayfayı aç.
        driver.get(getClass().getResource("/shadow-dom-example.html").toExternalForm());

        // Shadow DOM'un sahibi olan custom element'i (shadow host) sayfada bekle.
        WebElement shadowHost = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("consent-panel")));

        // Normal driver.findElement araması Shadow DOM sınırını geçemez; kökü host üzerinden al.
        SearchContext shadowRoot = shadowHost.getShadowRoot();

        // Butonu Shadow Root'un içinden bul ve tıkla.
        shadowRoot.findElement(By.cssSelector("#accept-all")).click();

        // Tıklama sonucunu bekle; test yalnızca click işleminin çalıştığını varsaymaz.
        WebElement result = wait.until(d -> shadowRoot.findElement(By.cssSelector("#result")));

        // Kullanıcı arayüzünde beklenen sonucun oluştuğunu doğrula.
        assertEquals("Consent accepted", result.getText());
        waitAndClose();
    }
}
