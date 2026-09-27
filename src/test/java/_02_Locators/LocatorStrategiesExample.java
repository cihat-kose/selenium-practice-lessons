package _02_Locators;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.BaseDriver;

import java.util.List;

/**
 * Finds the sample contact form controls with different Selenium locator strategies.
 * The test checks the element details so each locator is tied to a clear result.
 */
public class LocatorStrategiesExample extends BaseDriver {

    private static final String LOCATORS_PAGE =
            "https://www.selenium.dev/selenium/web/locators_tests/locators.html";

    @Test
    public void findElementsUsingDifferentLocatorStrategies() {
        driver.get(LOCATORS_PAGE);

        // Class name finds the first field that uses the shared "information" class.
        WebElement firstName = driver.findElement(By.className("information"));
        Assert.assertEquals("fname", firstName.getAttribute("id"));
        Assert.assertEquals("Jane", firstName.getAttribute("value"));

        // findElements returns both fields that share this class.
        List<WebElement> informationFields = driver.findElements(By.className("information"));
        Assert.assertEquals(2, informationFields.size());

        // ID locates the last-name field directly.
        WebElement lastName = driver.findElement(By.id("lname"));
        Assert.assertEquals("Doe", lastName.getAttribute("value"));

        // Name locates the newsletter checkbox.
        WebElement newsletter = driver.findElement(By.name("newsletter"));
        Assert.assertEquals("checkbox", newsletter.getAttribute("type"));

        // Link text locates the Selenium link and lets us verify its destination.
        WebElement seleniumLink = driver.findElement(By.linkText("Selenium Official Page"));
        Assert.assertEquals("https://www.selenium.dev/", seleniumLink.getAttribute("href"));

        waitAndClose();
    }
}
