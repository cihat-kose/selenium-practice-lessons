package _05_Select;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utility.BaseDriver;
import utility.MyFunction;

import java.util.List;

public class SelectDropdownAllMethodsTask extends BaseDriver {
    @Test
    public void testSelectDropdown() {
        // Siteye gidiyoruz
        driver.get("https://the-internet.herokuapp.com/dropdown");

        // Dropdown menüsünü buluyoruz
        WebElement dropdown = driver.findElement(By.id("dropdown"));

        // Drodown menusunde Select sinifini kullaniyoruz
        Select select = new Select(dropdown);

        // 1. Yöntem: Görünen metne göre seçim yapmak (selectByVisibleText)
        select.selectByVisibleText("Option 2");

        // Secimin dogru olup olmadigini dogruluyoruz
        WebElement selectedOption1 = select.getFirstSelectedOption();
        String selectedText1 = selectedOption1.getText();
        System.out.println("Seçilen Değer (Görünen Metin): = " + selectedText1);
        Assert.assertEquals("Option 2", selectedText1);

        MyFunction.wait(5);
        // 2. Yöntem: Value attribute'una göre seçim yapmak (selectByValue)
        select.selectByValue("1");

        WebElement selectedOption2 = select.getFirstSelectedOption();
        String selectedText2 = selectedOption2.getText();
        System.out.println("Seçilen Değer (Value Attribute): = " + selectedText2);
        Assert.assertEquals("Option 1", selectedText2);

        MyFunction.wait(5);
        // 3. Yöntem: Index numarasına göre seçim yapmak (selectByIndex)
        select.selectByIndex(2); // Option 2'nin index numarası 2
        WebElement selectedOption3 = select.getFirstSelectedOption();
        String selectedText3 = selectedOption3.getText();
        System.out.println("Seçilen Değer (Index): = " + selectedText3);
        Assert.assertEquals("Option 2", selectedText3);

        MyFunction.wait(5);
        // 4. Yöntem: Dropdown'daki tüm seçenekleri almak (getOptions)
        List<WebElement> allOptions = select.getOptions();
        System.out.println("Dropdown'daki Tüm Seçenekler:");
        for (WebElement option : allOptions) {
            System.out.println(option.getText());
        }

        MyFunction.wait(5);
        // 5. Yöntem: Ilksecili olan seçeneği almak (getFirstSelectedOption)
        WebElement firstSelectedOption = select.getFirstSelectedOption();
        String firstSelectedText = firstSelectedOption.getText();
        System.out.println("İlk Seçili Olan Seçenek: = " + firstSelectedText);

        // Eğer dropdown multiple selection destekleseydi, aşağıdaki yöntem kullanılabilirdi:
        // select.deselectAll(); // Tüm seçimleri kaldırır (Multiple selection dropdown'larda geçerli)

        waitAndClose();
    }
}
