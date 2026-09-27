package _06_Actions;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utility.BaseDriver;
import utility.MyFunction;


public class MouseActionsClass extends BaseDriver {

    Actions actions = new Actions(driver);

    /**
     * Fare Aksiyonları: Bir elementin üzerine fareyi getirme (Mouse Hover)
     */
    @Test
    public void hoverOverTest() {
        driver.get("http://the-internet.herokuapp.com/hovers");

        MyFunction.wait(2);
        WebElement elementToHover = driver.findElement(By.xpath("(//div[@class='figure'])[1]/img"));
        actions.moveToElement(elementToHover).perform();  // Fareyi element üzerine getir

//      Alternatif: fareyi elementin belli bir noktası üzerine getirme
//      actions.moveToElement(elementToHover, 50, 50).perform();

        waitAndClose();
    }

    /**
     * Fare Aksiyonları: Çift tıklama (Double Click)
     */
    @Test
    public void doubleClickTest() {
        // Testi gerçekleştireceğimiz sayfaya git
        driver.get("https://demoqa.com/buttons");

        MyFunction.wait(2);

        // Çift tıklama yapılacak element (Button)
        WebElement doubleClickButton = driver.findElement(By.id("doubleClickBtn"));

        // Fare ile çift tıklama işlemi
        actions.doubleClick(doubleClickButton).perform();  // Fare ile çift tıklama

//        Alternatif: Double click için önce element üzerine hareket edip çift tıklama
//        actions.moveToElement(doubleClickButton).doubleClick().perform();


        MyFunction.wait(2);
        // Geri bildirim mesajını doğrulama (Çift tıklama sonucu mesaj gösterildi mi?)
        WebElement message = driver.findElement(By.id("doubleClickMessage"));
        String expectedMessage = "You have done a double click";
        Assert.assertEquals(message.getText(), expectedMessage);

        waitAndClose();
    }

    /**
     * Fare Aksiyonları: Sağ tıklama (Right Click) ve Alert ile etkileşim
     */
    @Test
    public void rightClickTest() {
        driver.get("http://the-internet.herokuapp.com/context_menu");

        MyFunction.wait(2);
        WebElement elementToRightClick = driver.findElement(By.id("hot-spot"));
        actions.contextClick(elementToRightClick).perform();  // Sağ tıklama işlemi

        MyFunction.wait(2);

        // Açılan alert penceresini handle etme
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert içeriği: " + alert.getText());
        alert.accept(); // Alert penceresini kapatma

        waitAndClose();
    }

    /**
     * Fare Aksiyonları: Tıklama (Click)
     */
    @Test
    public void clickTest() {
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");

        MyFunction.wait(2);
        WebElement elementToClick = driver.findElement(By.xpath("//button[text()='Add Element']"));
        actions.click(elementToClick).perform();  // Elementi tıkla

//        Alternatif: actions.moveToElement() ile tıklama işlemi
//        actions.moveToElement(elementToClick).click().perform();

        waitAndClose();
    }

    /**
     * Fare Aksiyonları: Tıklayıp tutma ve bırakma (Click and Hold, Release)
     */
    @Test
    public void clickAndHoldReleaseTest() {
        driver.get("http://the-internet.herokuapp.com/drag_and_drop");

        MyFunction.wait(2);
        WebElement elementToClickAndHold = driver.findElement(By.id("column-a"));
        WebElement elementToDrop = driver.findElement(By.id("column-b"));

        MyFunction.wait(2);
        // Elementi tıklayıp tut ve hedefe bırak
        actions.clickAndHold(elementToClickAndHold).moveToElement(elementToDrop).release().perform();

//        Alternatif: Belirli piksel mesafesine sürükleyip bırakma (dragAndDropBy)
//        actions.clickAndHold(elementToClickAndHold).moveByOffset(150, 0).release().perform();

        waitAndClose();
    }

    /**
     * Fare Aksiyonları: Sürükle ve bırak (Drag and Drop)
     */
    @Test
    public void dragAndDropTest() {
        driver.get("http://the-internet.herokuapp.com/drag_and_drop");

        MyFunction.wait(2);
        WebElement sourceElement = driver.findElement(By.id("column-a"));
        WebElement targetElement = driver.findElement(By.id("column-b"));

        actions.dragAndDrop(sourceElement, targetElement).perform();  // Kaynak elemanı hedefe sürükle

        waitAndClose();
    }
}
