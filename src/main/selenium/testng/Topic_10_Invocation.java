package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_10_Invocation {

    WebDriver driver;

    @BeforeClass
    public void beforeClass(){

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test(invocationCount = 1) // Muốn chạy test case bnh lần ( nếu không set thì giá trị mặc định của nó là bằng 5 )
    public void Register(){

        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        // Register
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(2);

        String firstName = "Manual", lastname = "FC", emailAddress = getEmailAddress(), password = "123456789";
        String fullName = firstName + " " + lastname;

        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastname);
        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@title='Register']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class = 'success-msg']//li/span")).getText(), "Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, " + fullName + "!");

        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        // Logout
        driver.findElement(By.xpath("//div[@class='account-cart-wrapper']/a")).click();
        sleepInSeconds(2);
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
        sleepInSeconds(5);

        // Login
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@id='send2']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, " + fullName + "!");

        contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        // Verify Account
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='firstname']")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='lastname']")).getAttribute("value"), lastname);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']")).getAttribute("value"), emailAddress);

        // Logout
        driver.findElement(By.xpath("//div[@class='account-cart-wrapper']/a")).click();
        sleepInSeconds(2);
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();

        System.out.println("Email Address/ Password = " + emailAddress + " - " + password);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    public void sleepInSeconds(long timeInSecond){
        try {
            Thread.sleep(timeInSecond*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmailAddress(){
        Random random = new Random();
        return "automation" + random.nextInt(99999) + "@gmail.net";
    }

}
