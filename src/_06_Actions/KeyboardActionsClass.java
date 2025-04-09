package _06_Actions;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utility.BaseDriver;
import utility.MyFunction;


public class KeyboardActionsClass extends BaseDriver {

    Actions actions = new Actions(driver);

    /**
     * Klavye Aksiyonları: Tek bir tuşa basma (sendKeys)
     */
    @Test
    public void sendKeysTest() {
        driver.get("https://the-internet.herokuapp.com/key_presses");

        MyFunction.wait(2);
        WebElement bodyElement = driver.findElement(By.tagName("body"));
        bodyElement.sendKeys(Keys.SPACE);  // SPACE tuşuna basma

//        Alternatif: actions.sendKeys() kullanarak
//        actions.sendKeys(Keys.SPACE).perform();

        waitAndClose();
    }

    /**
     * Klavye Aksiyonları: CTRL + A kombinasyonu (Keys Chord)
     */
    @Test
    public void keyDownAndUpWithChordTest() {
        driver.get("https://the-internet.herokuapp.com/key_presses");

        MyFunction.wait(2);
        WebElement bodyElement = driver.findElement(By.tagName("body"));
        bodyElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));  // CTRL + A kombinasyonu

//        Alternatif: keyDown() ve keyUp() ile
//        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();

        waitAndClose();
    }

    /**
     * Klavye Aksiyonları: Birden fazla tuşa aynı anda basma (SHIFT + T)
     */
    @Test
    public void multipleKeysTest() {
        driver.get("https://the-internet.herokuapp.com/key_presses");

        MyFunction.wait(2);
        WebElement bodyElement = driver.findElement(By.tagName("body"));
        bodyElement.sendKeys(Keys.chord(Keys.SHIFT, "t"));  // SHIFT + T kombinasyonu (büyük harf T)

//        Alternatif: Doğrudan büyük T harfi yazma
//        bodyElement.sendKeys("T");

        waitAndClose();
    }

    /**
     * Klavye Aksiyonları: Zincirleme tuş kombinasyonları (CTRL + A ve ardından DELETE)
     */
    @Test
    public void keyCombinationChainTest() {
        driver.get("https://the-internet.herokuapp.com/key_presses");

        MyFunction.wait(2);
        WebElement bodyElement = driver.findElement(By.tagName("body"));

        // CTRL + A ve ardından DELETE tuşu simülasyonu
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();

//        Alternatif: Tek seferde chord ile
//        bodyElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

        waitAndClose();
    }
}
