package _05_Select_ElementInStatus;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utility.BaseDriver;

import java.util.List;

/** Selenium'un resmi form sayfasında native dropdown seçim yöntemlerini gösterir. */
public class SelectDropdownAllMethodsTask extends BaseDriver {
    @Test
    public void testSelectDropdown() {
        // Selenium'un örnek formunda tek bir native <select> menüsü vardır.
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        WebElement dropdown = driver.findElement(By.tagName("select"));
        Select select = new Select(dropdown);

        // Seçenek yazısına göre seçim yap ve sonucu kontrol et.
        select.selectByVisibleText("Two");
        Assert.assertEquals("Two", select.getFirstSelectedOption().getText());

        // HTML value değerine göre seçim yap.
        select.selectByValue("1");
        Assert.assertEquals("One", select.getFirstSelectedOption().getText());

        // Liste sırasındaki index 2'yi seç (ilk seçenek index 0'dır).
        select.selectByIndex(2);
        Assert.assertEquals("Two", select.getFirstSelectedOption().getText());

        // Seçili seçeneklerin tümünü al; bu menü tek seçimli olduğu için bir sonuç gelir.
        List<WebElement> selectedOptions = select.getAllSelectedOptions();
        Assert.assertEquals(1, selectedOptions.size());
        Assert.assertEquals("Two", selectedOptions.get(0).getText());

        // Menüdeki tüm seçenekleri oku. Bu sayfada açılış seçeneği ve üç değer vardır.
        List<WebElement> allOptions = select.getOptions();
        Assert.assertEquals(4, allOptions.size());
        for (WebElement option : allOptions) {
            System.out.println(option.getText());
        }

        // Deselect yöntemleri yalnızca çoklu seçim destekleyen select'lerde kullanılabilir.
        Assert.assertFalse("Bu örnekteki dropdown tek seçimlidir.", select.isMultiple());
        waitAndClose();
    }
}
