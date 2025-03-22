package _01_SeleniumIntro;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.BaseDriver;
import utility.MyFunction;

public class NinjaRegisterTest extends BaseDriver {

    @Test
    public void registerTest() {
        driver.get("http://tutorialsninja.com/demo/");

        MyFunction.wait(2);
        WebElement myAccount = driver.findElement(By.xpath("//span[text()='My Account']"));
        myAccount.click();

        MyFunction.wait(2);
        WebElement registerLink = driver.findElement(By.linkText("Register"));
        registerLink.click();

        MyFunction.wait(1);
        WebElement firstName = driver.findElement(By.id("input-firstname"));
        firstName.sendKeys("Kerem");

        MyFunction.wait(1);
        WebElement lastName = driver.findElement(By.id("input-lastname"));
        lastName.sendKeys("Mert");

        MyFunction.wait(1);
        WebElement eMail = driver.findElement(By.id("input-email"));
        eMail.sendKeys("test" + (int)(Math.random() * 10000) + "@test.com");

        MyFunction.wait(1);
        WebElement telephone = driver.findElement(By.id("input-telephone"));
        telephone.sendKeys("1234567890");

        MyFunction.wait(1);
        WebElement password= driver.findElement(By.id("input-password"));
        password.sendKeys("Password123");

        MyFunction.wait(1);
        WebElement confirmPassword=driver.findElement(By.id("input-confirm"));
        confirmPassword.sendKeys("Password123");

        MyFunction.wait(1);
        WebElement privacyPolicy=driver.findElement(By.name("agree"));
        privacyPolicy.click();

        MyFunction.wait(1);
        WebElement continueButton=driver.findElement(By.cssSelector("input[value='Continue']"));
        continueButton.click();

        waitAndClose();
    }
}
