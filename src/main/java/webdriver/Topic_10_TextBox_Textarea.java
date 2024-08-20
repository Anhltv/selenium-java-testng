package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_TextBox_Textarea {

    WebDriver driver;

    String firstName, lastName, emailAddress, password;

    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();

        firstName = "Joe";
        lastName = "Bidden";
        emailAddress = "joebiden";
        password = "123456789";
    }

    @Test
    public void TC_01_TechPanda() {

        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        driver.findElement(By.cssSelector("input#firstname")).sendKeys("Joe");
        driver.findElement(By.cssSelector("input#lastname")).sendKeys("Biden");
        driver.findElement(By.cssSelector("input#email_address")).sendKeys("");
        driver.findElement(By.cssSelector("input#password")).sendKeys("");
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys("");

        driver.findElement(By.xpath("//button[@title='Register']")).click();

        // Tuyệt đối
        Assert.assertEquals( driver.findElement(By.xpath("//ul[@class='messages']//span")).getText()
                , "Thank you for registering with Main Website Store.");

        String contactInformation = driver.findElement(By.xpath(
                "//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();

        // Tương đối
        Assert.assertTrue(contactInformation.contains("") && contactInformation.contains("")); // Full name + Email


    }

    @Test
    public void TC_02_() {

    }

    @Test
    public void TC_03_() {

    }
    @Test
    public void TC_04_() {

    }
    @Test
    public void TC_05_() {

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
