package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_WebElement_Exercise_I {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();

    }

    @Test
    public void TC_01_Displayed() {

        // isDisplayed: kiểm tra bất kì 1 element hiển thị nào
        // Hiển thị: có thể nhìn thấy - có kích thước cụ thể
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement emailTextBox = driver.findElement(By.cssSelector("input#mail"));

        if (emailTextBox.isDisplayed()){
            System.out.println("Email TextBox is displayed");
            emailTextBox.sendKeys("Automation Testing");
        } else {
            System.out.println("Email TextBox is not displayed");
        }

        WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));

        if (ageUnder18Radio.isDisplayed()){
            System.out.println("Age Under 18 Radio is displayed");
            ageUnder18Radio.click();
        } else {
            System.out.println("Age Under 18 Radio is not displayed");
        }

        WebElement educationTextArea = driver.findElement(By.cssSelector("textarea#edu"));

        if (educationTextArea.isDisplayed()){
            System.out.println("Education TextArea is displayed");
            educationTextArea.sendKeys("Automation Test");
        } else {
            System.out.println("Education TextArea is not displayed");
        }

        WebElement user5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));

        if (user5Text.isDisplayed()){
            System.out.println("User 5 name is displayed");
        } else {
            System.out.println("User 5 name is not displayed");
        }


    }

    @Test
    public void TC_02_Enabled() {

        // isEnable: kiểm tra bất kì 1 element nào có thể tương tác lên được # ngược lại với read-only (disabled)
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement emailTextBox = driver.findElement(By.cssSelector("input#mail"));
        if (emailTextBox.isEnabled()){
            System.out.println("Email TextBox is enabled");
        } else {
            System.out.println("Email TextBox is not enabled");
        }

        WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
        if (ageUnder18Radio.isEnabled()){
            System.out.println("Age Under 18 Radio is enabled");
        } else {
            System.out.println("Age Under 18 Radio is not enabled");
        }

        WebElement passwordTextBox = driver.findElement(By.cssSelector("input#disable_password"));
        if (passwordTextBox.isEnabled()){
            System.out.println("Password TextBox is enabled");
        } else {
            System.out.println("Password TextBox is not enabled");
        }

        WebElement biographyTextArea = driver.findElement(By.cssSelector("textarea#bio"));
        if (biographyTextArea.isEnabled()){
            System.out.println("Biography TextBox is enabled");
        } else {
            System.out.println("Biography TextBox is not enabled");
        }

    }

    @Test
    public void TC_03_Selected() {

        // isSelected: Kiểm tra 1 element được chọn thành công (Radio/ Checkbox/ Dropdown)
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
        if (ageUnder18Radio.isSelected()){
            System.out.println("Age Under 18 Radio is selected");
        } else {
            System.out.println("Age Under 18 Radio is not selected");
        }

        WebElement interestCheckBox = driver.findElement(By.cssSelector("input#development"));
        if (interestCheckBox.isSelected()){
            System.out.println("Interest Checkbox is selected");
        } else {
            System.out.println("Interest Checkbox is not selected");
        }

        ageUnder18Radio.click();
        interestCheckBox.click();

        if (ageUnder18Radio.isSelected()){
            System.out.println("Age Under 18 Radio is selected");
        } else {
            System.out.println("Age Under 18 Radio is not selected");
        }

        if (interestCheckBox.isSelected()){
            System.out.println("Interest Checkbox is selected");
        } else {
            System.out.println("Interest Checkbox is not selected");
        }

    }
    @Test
    public void TC_04_MailChimp_Validate() throws InterruptedException {

        driver.get("https://login.mailchimp.com/signup/");

        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc.vn@gmail.com");
        driver.findElement(By.cssSelector("input#email")).sendKeys(Keys.TAB);

        // Only number
        driver.findElement(By.id("new_password")).sendKeys("12345");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // Only lower text
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("testing");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // Only upper text
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("TESTING");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // Only special character
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("@!#@#$");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // Contains username
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("automation");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());

        // Full
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("123q!2#R");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);

        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

    }
    @Test
    public void TC_05_() {

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
