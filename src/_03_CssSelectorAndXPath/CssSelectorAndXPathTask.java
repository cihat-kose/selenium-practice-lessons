package _03_CssSelectorAndXPath;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.BaseDriver;

public class CssSelectorAndXPathTask extends BaseDriver {

    @Test
    public void testRegisterWithCssAndXpath(){
        //https://parabank.parasoft.com/parabank/index.htm adresine gidiniz.
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        //Register butonuna tıklayınız.
        WebElement registerBtn= driver.findElement(By.xpath("//a[text()='Register']"));
        registerBtn.click();

        //First Name alanına {firstName} giriniz.
        WebElement firstName= driver.findElement(By.cssSelector("input[id='customer.firstName']"));
        firstName.sendKeys("TestUser");

        //Last Name alanına {lastName} giriniz.
        WebElement lastName= driver.findElement(By.cssSelector("[id='customer.lastName']"));
        lastName.sendKeys("TestUserLastName");

        //Address alanına {address} giriniz.
        WebElement address= driver.findElement(By.cssSelector("input[id='customer.address.street']"));
        address.sendKeys("TestAddress 51St.");

        //City alanına {city} giriniz.
        WebElement city= driver.findElement(By.cssSelector("input[id='customer.address.city']"));
        city.sendKeys("TestCity");

        //State alanına {state} giriniz.
        WebElement state = driver.findElement(By.cssSelector("[id='customer.address.state']"));
        state.sendKeys("TestState");

        //Zip Code alanına {zipCode} giriniz.
        WebElement zipCode=driver.findElement(By.cssSelector("[id='customer.address.zipCode']"));
        zipCode.sendKeys("555555555");

        //Phone Number alanına {phoneNumber} giriniz.
        WebElement phoneNumber=driver.findElement(By.cssSelector("[id='customer.phoneNumber']"));
        phoneNumber.sendKeys("55555555");

        //SSN alanına {ssn} giriniz.
        WebElement ssn=driver.findElement(By.xpath("//input[@id='customer.ssn']"));
        ssn.sendKeys("5555555555");

        //Username alanına {username} giriniz.
        WebElement userName=driver.findElement(By.xpath("//input[@id='customer.username']"));
        userName.sendKeys("TestUsername12345");

        //Password alanına {password} giriniz.
        WebElement password=driver.findElement(By.xpath("//input[@id='customer.password']"));
        password.sendKeys("TestPassword123");

        //Confirm Password alanına {password} giriniz.
        WebElement confirmPassword=driver.findElement(By.xpath("//input[@id='repeatedPassword']"));
        confirmPassword.sendKeys("TestPassword123");

        //Register butonuna tıklayınız.
        WebElement registerButton=driver.findElement(By.xpath("//input[@value='Register']"));
        registerButton.click();

        //Başarılı bir şekilde kaydolduğunuzu doğrulayınız.
        WebElement successMessage = driver.findElement(By.xpath("//h1"));
        String actualMessage=successMessage.getText();
        System.out.println("Mesaj : "+actualMessage);
        Assert.assertTrue("Kayıt Başarılı değil! Beklenen Mesaj Bulunamadı. ",actualMessage.contains("Welcome"));

        //Belirli bir süre bekleyip tarayıcıyı kapatıyoruz
        waitAndClose();
    }
}
