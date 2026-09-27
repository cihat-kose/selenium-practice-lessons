package _13_ShadowDom;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchShadowRootException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Shadow DOM'a benzeyen ama native Shadow Root kullanmayan bir web component'i inceler.
 * İçindeki input alanına normal DOM aramasıyla ulaşılabildiğini doğrular.
 */
public class NonNativeCustomElementTest extends BaseDriver {

    @Test
    public void interactWithCustomElementWithoutShadowRoot() {
        driver.get("https://itytest.ccngroup.com.tr/Era/LoginPage?ReturnUrl=%2FEra");

        // dxbl-input-editor bileşenini bul ve native Shadow Root olmadığını doğrula.
        WebElement customInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("dxbl-input-editor")));
        assertThrows(NoSuchShadowRootException.class, customInput::getShadowRoot);

        // Gerçek Shadow DOM sınırı olmadığından, alt input normal DOM ile bulunabilir.
        WebElement usernameField = customInput.findElement(By.cssSelector("input"));
        wait.until(ExpectedConditions.elementToBeClickable(usernameField)).sendKeys("username");

        assertEquals("username", usernameField.getAttribute("value"));
        waitAndClose();
    }
}
