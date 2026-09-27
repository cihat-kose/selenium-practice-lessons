package _04_XPath;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.BaseDriver;

/**
 * Uses XPath expressions to find controls on Selenium's locator demo page.
 * It selects the newsletter checkbox and verifies the resulting selected state.
 */
public class XPathLocatorTask extends BaseDriver {

    private static final String LOCATORS_PAGE =
            "https://www.selenium.dev/selenium/web/locators_tests/locators.html";

    @Test
    public void findAndSelectElementsWithXPath() {
        driver.get(LOCATORS_PAGE);

        // XPath matches the first-name field by its ID.
        WebElement firstName = driver.findElement(By.xpath("//input[@id='fname']"));
        Assert.assertEquals("Jane", firstName.getAttribute("value"));

        // Select the newsletter checkbox using both its name and type.
        WebElement newsletter = driver.findElement(
                By.xpath("//input[@name='newsletter' and @type='checkbox']"));
        Assert.assertFalse(newsletter.isSelected());
        newsletter.click();
        Assert.assertTrue(newsletter.isSelected());

        // Find the Selenium link by its visible text and verify where it leads.
        WebElement seleniumLink = driver.findElement(
                By.xpath("//a[normalize-space()='Selenium Official Page']"));
        Assert.assertEquals("https://www.selenium.dev/", seleniumLink.getAttribute("href"));

        waitAndClose();
    }
}
