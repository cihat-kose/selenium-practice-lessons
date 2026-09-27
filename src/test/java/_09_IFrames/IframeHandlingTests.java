package _09_IFrames;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class IframeHandlingTests extends BaseDriver {

    /**
     Task 1:

     - "https://demoqa.com/frames" adresine gidin.
     - Sayfadaki iframe sayısını bulun ve ekrana yazdırın.
     */
    @Test
    public void printIframeCount() {
        driver.get("https://demoqa.com/frames");

        List<WebElement> consent = driver.findElements(By.xpath("//button[@class='fc-button fc-cta-consent fc-primary-button']//p"));
        if (!consent.isEmpty()) {
            consent.get(0).click();
        }

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("iframe"), 1));
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

        System.out.println("Sayfadaki iframe sayısı: " + iframes.size());
        assertTrue("DemoQA sayfasında en az iki iframe bekleniyordu.", iframes.size() >= 2);

        waitAndClose();
    }

    /**
     Task 2:

     - "https://demoqa.com/frames" adresine gidin.
     - Iframe içindeki "This is a sample page" metnini ekrana yazdırın.
     */
    @Test
    public void printIframeText() {
        driver.get("https://demoqa.com/frames");

        List<WebElement> consent = driver.findElements(By.xpath("//button[@class='fc-button fc-cta-consent fc-primary-button']//p"));
        if (!consent.isEmpty()) {
            consent.get(0).click();
        }

        WebElement iframe = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("frame1")));
        driver.switchTo().frame(iframe);

        WebElement text = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("sampleHeading")));
        assertTrue("Iframe metni beklenen içeriği taşımıyor.",
                text.getText().contains("This is a sample page"));
        System.out.println("Iframe içindeki metin: " + text.getText());

        waitAndClose();
    }
}
