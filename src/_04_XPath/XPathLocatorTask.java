package _04_XPath;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.BaseDriver;

public class XPathLocatorTask extends BaseDriver {

    @Test
    public void testRegisterWithXPath() {
        // Siteye gidiyoruz
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        // 1. Register butonuna XPath ile tıklıyoruz (Attribute Contains)
        WebElement registerLink = driver.findElement(By.xpath("//a[contains(@href, 'register.htm')]"));
        registerLink.click();

        // 2. First Name alanına XPath ile "Ahmet" yazıyoruz (Exact Match)
        WebElement firstNameInput = driver.findElement(By.xpath("//input[@name='customer.firstName']"));
        firstNameInput.sendKeys("Ahmet");

        // 3. Last Name alanına XPath ile "Yılmaz" yazıyoruz (ID ile)
        WebElement lastNameInput = driver.findElement(By.xpath("//input[@id='customer.lastName']"));
        lastNameInput.sendKeys("Yılmaz");

        // 4. Address alanına XPath ile "123 Ana Cadde" yazıyoruz (ID ile)
        WebElement addressInput = driver.findElement(By.xpath("//input[@id='customer.address.street']"));
        addressInput.sendKeys("123 Ana Cadde");

        // 5. City alanına XPath ile "İstanbul" yazıyoruz (Name ile)
        WebElement cityInput = driver.findElement(By.xpath("//input[@name='customer.address.city']"));
        cityInput.sendKeys("İstanbul");

        // 6. State alanına XPath ile "İstanbul" yazıyoruz (ID ile)
        WebElement stateInput = driver.findElement(By.xpath("//input[@id='customer.address.state']"));
        stateInput.sendKeys("İstanbul");

        // 7. Zip Code alanına XPath ile "34000" yazıyoruz (ID ile)
        WebElement zipCodeInput = driver.findElement(By.xpath("//input[@id='customer.address.zipCode']"));
        zipCodeInput.sendKeys("34000");

        // 8. Phone Number alanına XPath ile "5551234567" yazıyoruz (ID ile)
        WebElement phoneNumberInput = driver.findElement(By.xpath("//input[@id='customer.phoneNumber']"));
        phoneNumberInput.sendKeys("5551234567");

        // 9. SSN alanına XPath ile "123456789" yazıyoruz (ID ile)
        WebElement ssnInput = driver.findElement(By.xpath("//input[@id='customer.ssn']"));
        ssnInput.sendKeys("123456789");

        // 10. Username alanına XPath ile "kullaniciadi" yazıyoruz (ID ile)
        WebElement usernameInput = driver.findElement(By.xpath("//input[@id='customer.username']"));
        usernameInput.sendKeys("kullaniciadi" + (int) (Math.random() * 10000));

        // 11. Password alanına XPath ile "Sifre123" yazıyoruz (ID ile)
        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='customer.password']"));
        passwordInput.sendKeys("Sifre123");

        // 12. Confirm Password alanına XPath ile "Sifre123" yazıyoruz (ID ile)
        WebElement confirmPasswordInput = driver.findElement(By.xpath("//input[@id='repeatedPassword']"));
        confirmPasswordInput.sendKeys("Sifre123");

        // 13. Register butonuna XPath ile tıklıyoruz (Value attribute ile)
        WebElement registerButton = driver.findElement(By.xpath("//input[@value='Register']"));
        registerButton.click();

        // Başarılı bir şekilde kaydolduğunuzu doğruluyoruz
        WebElement successMessage = driver.findElement(By.xpath("//h1[@class='title']"));
        String actualMessage = successMessage.getText();
        System.out.println("Mesaj: " + actualMessage);
        Assert.assertTrue("Kayıt başarılı değil! Beklenen mesaj bulunamadı.", actualMessage.contains("Welcome"));

        waitAndClose();
    }
}
