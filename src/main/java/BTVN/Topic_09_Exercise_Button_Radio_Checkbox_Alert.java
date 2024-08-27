package BTVN;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_09_Exercise_Button_Radio_Checkbox_Alert {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void TC_01_Button() {

        driver.get("https://www.fahasa.com/customer/account/create");

        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        By loginButton = By.cssSelector("button.fhs-btn-login");

        Assert.assertFalse(driver.findElement(loginButton).isEnabled());

        String loginBackgroundColor = driver.findElement(loginButton).getCssValue("background-color");
        Color loginButtonColor = Color.fromString(loginBackgroundColor);
        Assert.assertEquals(loginButtonColor.asHex().toUpperCase(), "#000000");

        driver.findElement(By.id("login_username")).sendKeys("Ella@gmail.com");
        driver.findElement(By.id("login_password")).sendKeys("123456789");

        Assert.assertTrue(driver.findElement(loginButton).isEnabled());


        Assert.assertEquals(Color.fromString(driver.findElement(loginButton)
                .getCssValue("background-color")).asHex().toUpperCase(), "#C92127");

    }

    @Test
    public void TC_02_Default_CheckBox_Radio() throws InterruptedException {

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        if (!driver.findElement(By.cssSelector("input#engine3")).isSelected()){
            driver.findElement(By.cssSelector("input#engine3")).click();
        }

        Assert.assertTrue(driver.findElement(By.cssSelector("input#engine3")).isSelected());

        if (driver.findElement(By.cssSelector("input#engine3")).isSelected()){
            driver.findElement(By.cssSelector("input#engine3")).click();
        }

        Assert.assertFalse(driver.findElement(By.cssSelector("input#engine3")).isSelected());

    }

    @Test
    public void TC_03_Default_CheckBox_Radio() {

        driver.get("");

    }

    @Test
    public void TC_04_Select_All_Checkboxes() {

        driver.get("https://automationfc.github.io/multiple-fields/");

        // Select all checkboxes
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input.form-checkbox"));

        // Click all checkboxes
        for (WebElement checkbox : checkboxes){
            if (!checkbox.isSelected()){
                checkbox.click();
            }
        }

        // Verify all checkboxes selected
        for (WebElement checkbox : checkboxes){
            Assert.assertTrue(checkbox.isSelected());
        }

        // Deselect all checkboxes
        for (WebElement checkbox : checkboxes){
            if (checkbox.isSelected()){
                checkbox.click();
            }
        }

        // Verify all checkboxes selected
        for (WebElement checkbox : checkboxes){
            Assert.assertFalse(checkbox.isSelected());
        }

        // Select 1 in all + verify
        driver.findElement(By.cssSelector("input[value='Heart Attack']")).click();
        driver.findElement(By.cssSelector("input[value='Sleep Apnea']")).click();
        driver.findElement(By.cssSelector("input[value='Emphysema']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Heart Attack']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Sleep Apnea']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Emphysema']")).isSelected());

        for (WebElement checkbox : checkboxes){
            if (!checkbox.isSelected() && checkbox.getAttribute("value").equals("Emphysema")){
                checkbox.click();
            }
        }
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Emphysema']")).getAttribute("value").equals("Emphysema"));
    }

    @Test
    public void TC_05_Custom_Checkbox_Radio() {

        driver.get("https://login.ubuntu.com/");

        By newUserRadio = By.cssSelector("input#id_new_user");

        Assert.assertFalse(driver.findElement(newUserRadio).isSelected());

        newUserRadio = By.cssSelector("label.new-user");

    }

    @Test
    public void TC_06_() {

    }

    @Test
    public void TC_07_() {

    }

    @Test
    public void TC_08_() {

    }

    @Test
    public void TC_09_() {

    }

    @Test
    public void TC_10_() {

    }

    @Test
    public void TC_11_() {

    }

    @Test
    public void TC_12_() {

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
