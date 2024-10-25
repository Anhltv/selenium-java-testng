package webdriver_31;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_03_Relative_Locator {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com");

    }
    @Test
    public void TC_01_Relative_Locator(){

        driver.get("http://live.techpanda.org/index.php/catalogsearch/advanced/");

        // 1 - Khai báo biến
        // Khi cái biến này được sử dụng nhiều lần
        String emailAddress = "automationtest@gmail.com";
//        By emailTextBoxBy = By.cssSelector("");
//        WebElement emailTextBoxElement = driver.findElement(emailTextBoxBy);
//
//        emailTextBoxElement.clear();
//        emailTextBoxElement.isDisplayed();

        // 2 - Không khua báo biến
        // Khi biến chỉ sử dụng 1 lần
//        driver.findElement(emailTextBoxBy).sendKeys("");

        WebElement priceFromeElement = driver.findElement(RelativeLocator.with(By.tagName("input"))
                .toLeftOf(By.name("price[to]"))
                .below(By.id("sku"))
                .above(By.id("tax_class_id")));
        priceFromeElement.sendKeys("100");

        By customerServiceBy = By.xpath("//a[@contain(@href");


    }

    @Test
    public void TC_02_Xpath(){

        driver.get("https://demo.nopcommerce.com/register");

        // Your Personal Details
        driver.findElement(By.xpath("//label[@for='gender-male']"));
        driver.findElement(By.xpath("//label[@for='gender-female']"));
        driver.findElement(By.xpath("//input[@id='FirstName']"));
        driver.findElement(By.xpath("//input[@id='LastName']"));
        driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"));
        driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"));
        driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"));
        driver.findElement(By.xpath("//input[@id='Email']"));


        // Company Details
        driver.findElement(By.xpath("//input[@id='Company']"));

        // Options
        driver.findElement(By.xpath("//input[@id='Newsletter']"));

        // Your Passwords
        driver.findElement(By.xpath("//input[@id='Password']"));
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']"));

        // Register button
        driver.findElement(By.xpath("//button[@id='register-button']"));

    }

    @Test
    public void TC_03(){

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

}
