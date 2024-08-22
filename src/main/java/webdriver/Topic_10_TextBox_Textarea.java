package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_10_TextBox_Textarea {

    WebDriver driver;

    Random rand;


    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();
        rand = new Random();

    }

    //    @Test
    public void TC_01_TechPanda() throws InterruptedException {

        String firstName, lastName, emailAddress, password, fullName;

        firstName = "Joe";
        lastName = "Bidden";
        fullName = firstName + " " + lastName;
        emailAddress = "joebiden" + rand.nextInt(99999) + "@gmail.com";
        password = "123456789";

        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);

        driver.findElement(By.xpath("//button[@title='Register']")).click();

        // Tuyệt đối
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText()
                , "Thank you for registering with Main Website Store.");

        String contactInformation = driver.findElement(By.xpath(
                "//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();

        // Tương đối
        Assert.assertTrue(contactInformation.contains(fullName) && contactInformation.contains(emailAddress)); // Full name + Email

        driver.findElement(By.xpath("//h3[text()='Contact Information']/following-sibling::a")).click();

        Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), emailAddress);

        // Product Review
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//h2/a[@title='Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();
        driver.findElement(By.xpath("//input[@id='Quality 1_5']")).click();
        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys("Good application\n" +
                "Pretty easy to navigate.");
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys("Good Product");
        driver.findElement(By.cssSelector("input#nickname_field")).sendKeys(fullName);
        driver.findElement(By.xpath("//button[@title='Submit Review']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText()
                , "Your review has been accepted for moderation.");

        // Log out
        driver.findElement(By.cssSelector("div.account-cart-wrapper>a")).click();
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();

        Thread.sleep(6000);

        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/");

    }

    //    @Test
    public void TC_02_OrangeHRM() throws InterruptedException {

        String firstName, lastName, userName, password, number, comments;

        firstName = "Automation";
        lastName = "FC";
        userName = "automation" + rand.nextInt(999) + "fc";
        password = "VanAnh@2311";
        number = rand.nextInt(999) + "-" + rand.nextInt(999) + "-" + rand.nextInt(999);
        comments = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();

        driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();

        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();

        String employeeID = driver.findElement(By
                        .xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
                .getAttribute("value");

        driver.findElement(By.name("firstName")).sendKeys(firstName);
        driver.findElement(By.name("lastName")).sendKeys(lastName);

        driver.findElement(By.xpath("//input[@type='checkbox']/following-sibling::span")).click();

        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(userName);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);

        driver.findElement(By.xpath("//button[text()= ' Save ']")).click();

        Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By
                        .xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
                .getAttribute("value"), employeeID);

        driver.findElement(By.xpath("//a[text()='Immigration']")).click();

        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();

        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(number);
        driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).sendKeys(comments);

        driver.findElement(By.xpath("//button[text()=' Save ']")).click();

        driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']/parent::button")).click();
        Thread.sleep(5000);

        Assert.assertEquals(driver
                .findElement(By.xpath("//label[text()='Number']/following::div[1]/input")).getAttribute("value"), number);
        Assert.assertEquals(driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).getAttribute("value"), comments);

        driver.findElement(By.xpath("//img[@class='oxd-userdropdown-img']")).click();

        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        driver.findElement(By.name("username")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();

        driver.findElement(By.xpath("//span[text()='My Info']")).click();
        Thread.sleep(5000);

        Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By
                        .xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
                .getAttribute("value"), employeeID);

        driver.findElement(By.xpath("//a[text()='Immigration']")).click();

        driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']/parent::button")).click();
        Thread.sleep(5000);

        Assert.assertEquals(driver
                .findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input"))
                .getAttribute("value"), number);
        Assert.assertEquals(driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).getAttribute("value"), comments);

    }

    //    @Test
    public void TC_03_GuRu99() throws InterruptedException {

        String userName, passw;

        userName = "mngr586920";
        passw = "mumyjar";

        String customerName, dateOfBirth, address, city, state, pin, mobileNumber, email, password;

        customerName = "Ella";
        dateOfBirth = "2024-08-21";
        address = "New York";
        city = "Seoul";
        state = "Nope";
        pin = "231120";
        mobileNumber = "0987654321";
        email = "ella" + rand.nextInt(99999) + "@mail.txt";
        password = "1234567@Ella";

        driver.get("http://demo.guru99.com/v4");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.name("uid")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys(passw);

        driver.findElement(By.name("btnLogin")).click();

        driver.findElement(By.xpath("//a[text()='New Customer']")).click();

        Thread.sleep(3000);

        JavascriptExecutor jsExecutor;
        jsExecutor = (JavascriptExecutor) driver;
        driver.findElement(By.name("name")).sendKeys(customerName);
        WebElement dobTextbox = driver.findElement(By.name("dob"));
        jsExecutor.executeScript("arguments[0].removeAttribute('type')", dobTextbox);
        Thread.sleep(3000);
        dobTextbox.sendKeys(dateOfBirth);
        driver.findElement(By.name("addr")).sendKeys(address);
        driver.findElement(By.name("city")).sendKeys(city);
        driver.findElement(By.name("state")).sendKeys(state);
        driver.findElement(By.name("pinno")).sendKeys(pin);
        driver.findElement(By.name("telephoneno")).sendKeys(mobileNumber);
        driver.findElement(By.name("emailid")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(By.name("sub")).click();

        String customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobileNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);

        driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();

        driver.findElement(By.name("cusid")).sendKeys(customerID);
        driver.findElement(By.name("AccSubmit")).click();

        Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"), customerName);
        Assert.assertEquals(driver.findElement(By.name("addr")).getAttribute("value"), address);

        String editAddress = "Daegu";
        String editCity = "Busan";
        String editState = "Yes";
        String editPin = "191120";
        String editMobile = "0912833345";
        String editEmail = "rozalin" + rand.nextInt(99999) + "@mail.txt";

        driver.findElement(By.name("addr")).clear();
        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("state")).clear();
        driver.findElement(By.name("pinno")).clear();
        driver.findElement(By.name("telephoneno")).clear();
        driver.findElement(By.name("emailid")).clear();

        driver.findElement(By.name("addr")).sendKeys(editAddress);
        driver.findElement(By.name("city")).sendKeys(editCity);
        driver.findElement(By.name("state")).sendKeys(editState);
        driver.findElement(By.name("pinno")).sendKeys(editPin);
        driver.findElement(By.name("telephoneno")).sendKeys(editMobile);
        driver.findElement(By.name("emailid")).sendKeys(editEmail);

        driver.findElement(By.name("sub")).click();

        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editMobile);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editMobile);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);

    }

    @Test
    public void TC_06_() {

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
