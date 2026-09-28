package _13_ShadowDom;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

import static org.junit.Assert.assertTrue;

/**
 * Akakçe'deki çerez onayı bileşeninin açık Shadow DOM'una erişir.
 * Gerçek bir sayfada getShadowRoot() kullanımını gösterir.
 */
public class AkakceConsentShadowDomTest extends BaseDriver {

    @Test
    public void acceptConsentInsideShadowRoot() {
        driver.get("https://www.akakce.com/");

        // Çerez arayüzü yüklenene kadar Shadow DOM host'unu bekle.
        WebElement shadowHost = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("efilli-layout-dynamic")));

        // Shadow DOM içindeki elementler host'un kökü SearchContext'i üzerinden aranır.
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        WebElement acceptButton = wait.until(d -> {
            WebElement button = shadowRoot.findElement(By.cssSelector("div[data-name='kabul et']"));
            return button.isDisplayed() && button.isEnabled() ? button : null;
        });

        // Onayla ve arayüzün kapandığını doğrula.
        acceptButton.click();
        assertTrue("Consent button should disappear after accepting",
                wait.until(ExpectedConditions.invisibilityOf(acceptButton)));

        waitAndClose();
    }
}
