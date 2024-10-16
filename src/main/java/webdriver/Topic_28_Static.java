package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_28_Static {

    WebDriver driver;


    // Static hay là HardWait là wait cứng
    // Chỉ apply ở mức thử nghiệm/ implement testcase - không dùng bừa bãi
    // Sử dụng với Windows/ Tab khi wait cho page mới load ra thành công (switch bằng title có nhiều hơn 2 window/ tab)
    // + WebDriverWait không có hàm để wait cho all page load thành công
    // + pageLoadTimeout() -> Cho 1 page (tab/window) mà drive đang active
    // Upload multiple file - cần sleep cứng sau mỗi lần upload từng file
    // Sử dụng với Internet Exlorer browse khi chuyển page hoặc thao tác mà có gửi request lên server làm tải lại trang.

    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();
//        driver.manage().window().maximize();
        driver.get("https://automationfc.github.io/dynamic-loading/");

    }

    @Test
    public void TC_01_Dont_Set() {

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void TC_02_Less_Than() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.findElement(By.cssSelector("div#start>button")).click();

        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void TC_03_Equal() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.cssSelector("div#start>button")).click();

        Thread.sleep(5000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void TC_04_Greater_Than() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.findElement(By.cssSelector("div#start>button")).click();

        Thread.sleep(10000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void TC_05_() {

    }

    @Test
    public void TC_06_() {

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
