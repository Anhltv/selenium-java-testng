package BTVN;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_05_Xpath_Css {

    WebDriver driver;


    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


    }

    @Test
    public void TC_01_Register_With_Empty_Data() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Verify với dữ liệu mong muốn
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

    }

    @Test
    public void TC_02_Register_With_Invalid_Email() {

        driver.findElement(By.id("txtFirstname")).sendKeys("Ella");
        driver.findElement(By.id("txtEmail")).sendKeys("123@456@789");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@456@789");
        driver.findElement(By.id("txtPassword")).sendKeys("23112003");
        driver.findElement(By.id("txtCPassword")).sendKeys("23112003");
        driver.findElement(By.id("txtPhone")).sendKeys("0987654321");

        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập email hợp lệ");

    }

    @Test
    public void TC_03_Register_With_Incorrect_Confirm_Email() {

        clear();

        driver.findElement(By.id("txtFirstname")).sendKeys("Ella");
        driver.findElement(By.id("txtEmail")).sendKeys("ella@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("ella1@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("23112003");
        driver.findElement(By.id("txtCPassword")).sendKeys("23112003");
        driver.findElement(By.id("txtPhone")).sendKeys("0987654321");

        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");

    }

    @Test
    public void TC_04_Register_With_Password_Less_Than_6Characters() {

        clear();

        driver.findElement(By.id("txtFirstname")).sendKeys("Ella");
        driver.findElement(By.id("txtEmail")).sendKeys("ella@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("ella@gmail.com");

        driver.findElement(By.id("txtPassword")).sendKeys("2311");
        driver.findElement(By.id("txtCPassword")).sendKeys("2311");
        driver.findElement(By.id("txtPhone")).sendKeys("0987654321");

        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");

    }

    @Test
    public void TC_05_Register_With_Incorrect_Confirm_Password() {

        clear();

        driver.findElement(By.id("txtFirstname")).sendKeys("Ella");
        driver.findElement(By.id("txtEmail")).sendKeys("ella@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("ella@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("23112003");
        driver.findElement(By.id("txtCPassword")).sendKeys("23112004");
        driver.findElement(By.id("txtPhone")).sendKeys("0987654321");

        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");

    }

    @Test
    public void TC_06_Register_With_Invalid_Phone_Number() {

        clear();

        driver.findElement(By.id("txtFirstname")).sendKeys("Ella");
        driver.findElement(By.id("txtEmail")).sendKeys("ella@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("ella@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("23112003");
        driver.findElement(By.id("txtCPassword")).sendKeys("23112004");
        driver.findElement(By.id("txtPhone")).sendKeys("45435");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: " +
                "09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");

    }

    public void clear() {
        driver.findElement(By.id("txtFirstname")).clear();
        driver.findElement(By.id("txtEmail")).clear();
        driver.findElement(By.id("txtCEmail")).clear();
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.id("txtPhone")).clear();
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
