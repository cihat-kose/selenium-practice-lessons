package _02_Locators;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.BaseDriver;
import utility.MyFunction;

public class LocatorStrategiesExample extends BaseDriver {

    @Test
    public void testRegister() {
        // 1. Adım: "https://parabank.parasoft.com/parabank/index.htm" adresine gidiniz.
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        // 2. Adım: "Register" butonuna tıklayın (By.linkText() ile buluyoruz)
        WebElement registerLink = driver.findElement(By.linkText("Register"));
        registerLink.click();

        // 3. Adım: "First Name" alanına {firstName} girin (By.name() ile buluyoruz)
        MyFunction.wait(1);  // Neticeyi görmek için kısa bekleme
        WebElement firstNameInput = driver.findElement(By.name("customer.firstName"));
        firstNameInput.sendKeys("Ahmet");

        // 4. Adım: "Last Name" alanına {lastName} girin
        MyFunction.wait(1);  // Neticeyi görmek için kısa bekleme
        WebElement lastNameInput = driver.findElement(By.id("customer.lastName"));
        lastNameInput.sendKeys("Yılmaz");

        // 5. Adım: "Address" alanına {address} girin
        MyFunction.wait(1);  // Neticeyi görmek için kısa bekleme
        WebElement addressInput = driver.findElement(By.id("customer.address.street"));
        addressInput.sendKeys("123 Ana Cadde");

        // 6. Adım: "City" alanına {city} girin
        MyFunction.wait(1);  // Neticeyi görmek için kısa bekleme
        WebElement cityInput = driver.findElement(By.name("customer.address.city"));
        cityInput.sendKeys("İstanbul");

        // 7. Adım: "State" alanına {state} girin
        MyFunction.wait(1);  // Neticeyi görmek için kısa bekleme
        WebElement stateInput = driver.findElement(By.id("customer.address.state"));
        stateInput.sendKeys("İstanbul");

        // 8. Adım: "Zip Code" alanına {zipCode} girin
        MyFunction.wait(1);  // Neticeyi görmek için kısa bekleme
        WebElement zipCodeInput = driver.findElement(By.id("customer.address.zipCode"));
        zipCodeInput.sendKeys("34000");

        // 9. Adım: "Phone Number" alanına {phoneNumber} girin
        MyFunction.wait(1);  // Neticeyi görmek için kısa bekleme
        WebElement phoneNumberInput = driver.findElement(By.id("customer.phoneNumber"));
        phoneNumberInput.sendKeys("5551234567");

        // 10. Adım: "SSN" alanına {ssn} girin
        MyFunction.wait(1);  // Neticeyi görmek için kısa bekleme
        WebElement ssnInput = driver.findElement(By.id("customer.ssn"));
        ssnInput.sendKeys("123456789");

        // 11. Adım: "Username" alanına {username} girin
        MyFunction.wait(1);  // Neticeyi görmek için kısa bekleme
        WebElement usernameInput = driver.findElement(By.id("customer.username"));
        usernameInput.sendKeys("kullaniciadi" + (int) (Math.random() * 10000));

        // 12. Adım: "Password" alanına {password} girin
        MyFunction.wait(1);  // Neticeyi görmek için kısa bekleme
        WebElement passwordInput = driver.findElement(By.id("customer.password"));
        passwordInput.sendKeys("Sifre123");

        // 13. Adım: "Confirm Password" alanına {password} tekrar girin
        MyFunction.wait(1);  // Neticeyi görmek için kısa bekleme
        WebElement confirmPasswordInput = driver.findElement(By.id("repeatedPassword"));
        confirmPasswordInput.sendKeys("Sifre123");

        // 14. Adım: "Register" butonuna tıklayın (By.cssSelector() ile buluyoruz)
        MyFunction.wait(1);  // Neticeyi görmek için kısa bekleme
        WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
        registerButton.click();

        // 15. Adım: Başarılı bir şekilde kaydolduğunuzu doğrulayın
        MyFunction.wait(1);  // Neticeyi görmek için kısa bekleme
        WebElement successMessage = driver.findElement(By.className("title"));

        // Mesajı kontrol etmeden önce konsola yazdırıyoruz
        String actualMessage = successMessage.getText();
        System.out.println("Mesaj: " + actualMessage);

        // JUnit ile assertion yapıyoruz (Başarılı kaydolma mesajını kontrol ediyoruz)
        Assert.assertTrue("Kayıt başarılı değil! Beklenen mesaj bulunamadı.", actualMessage.contains("Welcome"));

        // Belirli bir süre bekleyip tarayıcıyı kapatıyoruz
        waitAndClose();
    }
}
