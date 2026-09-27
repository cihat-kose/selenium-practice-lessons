package _13_ShadowDom;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

import static org.junit.Assert.assertEquals;

/** Demonstrates Selenium's native WebElement.getShadowRoot() support. */
public class ShadowDomExampleTest extends BaseDriver {

    @Test
    public void clickButtonInsideOpenShadowRoot() {
        driver.get(getClass().getResource("/shadow-dom-example.html").toExternalForm());

        WebElement shadowHost = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("consent-panel")));
        SearchContext shadowRoot = shadowHost.getShadowRoot();

        shadowRoot.findElement(By.cssSelector("#accept-all")).click();
        WebElement result = wait.until(d -> shadowRoot.findElement(By.cssSelector("#result")));

        assertEquals("Consent accepted", result.getText());
        waitAndClose();
    }
}
