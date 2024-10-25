package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_30_Explicit_Loading {

    WebDriver driver;

    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();

        // 1 điều kiện ở step sau được xuất hiện
        // 1 điều kiện ở step trước đó được hoàn thành/ biến mất

        //**
        // Luận điểm 1: Cách set timeout
        // - Nếu timeout mình set không đủ thì nó vẫn fail như thường
        // - Set vừa đủ -> Pass
        // - Set dư timeout -> Không cần chờ hết timeout
        // */

        //**
        // Luận điểm 2: 1 bài toán có thể dùng 1 hoặc nhiều cách wait
        // Cần chọn cách nào phù hợp và tối ưu
        // */

        //**
        // Tip
        // Khi wait xong nó sẽ trả về 1 kiểu dữ liệu tương ứng (boolean/ element/ elements/...)
        // Trả về điều kiện mình đăng cần
        // */

    }

    @Test
    public void TC_01_Less_Than() {

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        // Điều kiện Wait
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void TC_02_Equal() {

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void TC_03_Greater_Than() {

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }
    @Test
    public void TC_04_() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        // Visible (dành cho 1 element sau được xuất hiện)
        WebElement helloText = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
        Assert.assertEquals(helloText.getText(), "Hello World!");

        // Invisible (dành cho 1 element sắp biến mất/ kì vọng biến mất đi)
        boolean loadingIconStatus = explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        Assert.assertFalse(loadingIconStatus);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

        // Text
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"), "Hello World!"));

    }
    @Test
    public void TC_05_() {

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
