package _09_IFrames;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;
import utility.MyFunction;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IframeHandlingTests extends BaseDriver {
    private static final String IFRAME_PAGE = "https://www.selenium.dev/selenium/web/iframes.html";

    @Test
    public void printIframeCount() {
        driver.get(IFRAME_PAGE);

        // Bu Selenium örnek sayfasında en az bir iframe bulunmasını doğrula.
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("Sayfadaki iframe sayısı: " + iframes.size());
        assertTrue("Selenium örnek sayfasında iframe bulunmalı.", !iframes.isEmpty());

        waitAndClose();
    }

    @Test
    public void printIframeText() {
        driver.get(IFRAME_PAGE);

        // Önce iframe element'ini ana sayfada bul, sonra Selenium odağını içine taşı.
        WebElement iframe = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("iframe1")));
        driver.switchTo().frame(iframe);

        MyFunction.wait(1);

        // Bu yazı ve email alanı iframe'in içindeki sayfaya aittir.
        assertTrue(driver.getPageSource().contains("We Leave From Here"));
        WebElement email = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        email.sendKeys("student@example.com");
        assertEquals("student@example.com", email.getAttribute("value"));

        MyFunction.wait(1);

        // Ana sayfaya dön ve ana sayfa metninin yeniden erişilebilir olduğunu doğrula.
        driver.switchTo().defaultContent();
        assertTrue(driver.getPageSource().contains("This page has iframes"));

        waitAndClose();
    }
}
