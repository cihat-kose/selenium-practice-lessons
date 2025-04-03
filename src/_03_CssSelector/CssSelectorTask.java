package _03_CssSelector;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.BaseDriver;

public class CssSelectorTask extends BaseDriver {

    @Test
    public void testRegisterWithCssSelector() {
        // 1. Siteye gidiyoruz
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        // 2. Register butonuna CSS Selector ile tıklıyoruz (Attribute Contains)
        WebElement registerLink = driver.findElement(By.cssSelector("a[href*='register.htm']"));
        registerLink.click();

        // 3. First Name alanına CSS Selector ile "Ahmet" yazıyoruz (Name attribute ile)
        WebElement firstNameInput = driver.findElement(By.cssSelector("input[name='customer.firstName']"));
        firstNameInput.sendKeys("Ahmet");

        // 4. Last Name alanına CSS Selector ile "Yılmaz" yazıyoruz (ID attribute ile)
        WebElement lastNameInput = driver.findElement(By.cssSelector("#customer\\.lastName"));
        lastNameInput.sendKeys("Yılmaz");

        // 5. Address alanına CSS Selector ile "123 Ana Cadde" yazıyoruz (ID attribute ile)
        WebElement addressInput = driver.findElement(By.cssSelector("#customer\\.address\\.street"));
        addressInput.sendKeys("123 Ana Cadde");

        // 6. City alanına CSS Selector ile "İstanbul" yazıyoruz (Name attribute ile)
        WebElement cityInput = driver.findElement(By.cssSelector("input[name='customer.address.city']"));
        cityInput.sendKeys("İstanbul");

        // 7. State alanına CSS Selector ile "İstanbul" yazıyoruz (ID attribute ile)
        WebElement stateInput = driver.findElement(By.cssSelector("#customer\\.address\\.state"));
        stateInput.sendKeys("İstanbul");

        // 8. Zip Code alanına CSS Selector ile "34000" yazıyoruz (ID attribute ile)
        WebElement zipCodeInput = driver.findElement(By.cssSelector("#customer\\.address\\.zipCode"));
        zipCodeInput.sendKeys("34000");

        // 9. Phone Number alanına CSS Selector ile "5551234567" yazıyoruz (ID attribute ile)
        WebElement phoneNumberInput = driver.findElement(By.cssSelector("#customer\\.phoneNumber"));
        phoneNumberInput.sendKeys("5551234567");

        // 10. SSN alanına CSS Selector ile "123456789" yazıyoruz (ID attribute ile)
        WebElement ssnInput = driver.findElement(By.cssSelector("#customer\\.ssn"));
        ssnInput.sendKeys("123456789");

        // 11. Username alanına CSS Selector ile "kullaniciadi" yazıyoruz (ID attribute ile)
        WebElement usernameInput = driver.findElement(By.cssSelector("#customer\\.username"));
        usernameInput.sendKeys("kullaniciadi" + (int) (Math.random() * 10000));

        // 12. Password alanına CSS Selector ile "Sifre123" yazıyoruz (ID attribute ile)
        WebElement passwordInput = driver.findElement(By.cssSelector("#customer\\.password"));
        passwordInput.sendKeys("Sifre123");

        // 13. Confirm Password alanına CSS Selector ile "Sifre123" yazıyoruz (ID attribute ile)
        WebElement confirmPasswordInput = driver.findElement(By.cssSelector("#repeatedPassword"));
        confirmPasswordInput.sendKeys("Sifre123");

        // 14. Register butonuna CSS Selector ile tıklıyoruz (Button value attribute ile)
        WebElement registerButton = driver.findElement(By.cssSelector("input[value='Register']"));
        registerButton.click();

        // 15. Başarılı bir şekilde kaydolduğunuzu doğruluyoruz
        WebElement successMessage = driver.findElement(By.cssSelector(".title"));
        String actualMessage = successMessage.getText();
        System.out.println("Mesaj: " + actualMessage);
        Assert.assertTrue("Kayıt başarılı değil! Beklenen mesaj bulunamadı.", actualMessage.contains("Welcome"));

        // Belirli bir süre bekleyip tarayıcıyı kapatıyoruz
        waitAndClose();
    }
}
