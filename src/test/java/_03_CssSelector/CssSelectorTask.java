package _03_CssSelector;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.BaseDriver;

/**
 * Uses CSS selectors to find sample form controls, select the female radio button,
 * and check that Selenium marked it as selected.
 */
public class CssSelectorTask extends BaseDriver {

    private static final String LOCATORS_PAGE =
            "https://www.selenium.dev/selenium/web/locators_tests/locators.html";

    @Test
    public void findAndSelectElementsWithCssSelectors() {
        driver.get(LOCATORS_PAGE);

        // An ID selector finds the first-name field.
        WebElement firstName = driver.findElement(By.cssSelector("#fname"));
        Assert.assertEquals("Jane", firstName.getAttribute("value"));

        // A combined class and ID selector finds the last-name field.
        WebElement lastName = driver.findElement(By.cssSelector("input.information#lname"));
        Assert.assertEquals("Doe", lastName.getAttribute("value"));

        // Attribute selectors find the newsletter checkbox by its name and type.
        WebElement newsletter = driver.findElement(
                By.cssSelector("input[name='newsletter'][type='checkbox']"));
        Assert.assertFalse(newsletter.isSelected());

        // Select the Female radio option and verify that the browser selected it.
        WebElement femaleOption = driver.findElement(
                By.cssSelector("input[type='radio'][value='f']"));
        femaleOption.click();
        Assert.assertTrue(femaleOption.isSelected());

        waitAndClose();
    }
}
