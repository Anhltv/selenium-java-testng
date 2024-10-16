package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_26_Wait_FindElement {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        // Total Time = 13s
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));

    }

    @Test
    public void TC_01_FindElement() {

        driver.get("https://demo.nopcommerce.com/register");

        // 1 - Nếu tìm thấy duy nhất 1 element
        // Trả về đúng element đó
        // Không cần chờ hết time của implicit
        driver.findElement(By.cssSelector("input#FirstName"));

        // 2- Nếu tìm thấy nhiều hơn 1 element
        // Chỉ thao tác với element đầu tiên
        // Lưu ý là khi lấy locator nên check trước
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Testing...");

        // 3 - Nếu không tìm thấy element nào
        // Mới đầu vào findElement nhưng không thấy:
        // Tìm lại mà thấy element thì không cần chờ hết tổng time còn lại
        // Tìm lại và không thấy hết tổng time 13s thì đánh fail testcase
        // Show lỗi: NoSuchElementException
        driver.findElement((By.cssSelector("input#Selenium")));
    }

    @Test
    public void TC_02_FindElements() {

        List<WebElement> elements = null;

        // 1 - Nếu tìm thấy duy nhất 1 element
        // Trả về đúng 1 cái
        elements = driver.findElements(By.cssSelector("input#FirstName"));
        System.out.println(elements.size());
        // Tìm hết tất cả các element với locator
        // Bị ảnh hưởng bởi implicitWait
        // Nếu tìm thấy thì trả về ListElement bao gồm cáo element bên trong
        // Nếu không tìm thấy sẽ trả về List rỗng = 0

        // 2 - Nếu tìm thấy nhiều hơn 1 element
        // Trả về hết toàn bộ các element matching
        elements = driver.findElements(By.cssSelector("input[type='text']"));
        System.out.println(elements.size());

        // 3 - Nếu không tìm thấy element nào
        // Mới đầu vào findElement nhưng không thấy:
        // Tìm lại mà thấy element thì không cần chờ hết tổng time còn lại
        // Tìm lại và không thấy hết tổng time 13s thì đánh fail testcase
        // Show lỗi: NoSuchElementException
        // + Trả về List Element = 0
        // + KHÔNG ĐÁNH FAIL TESTCASE
        elements = driver.findElements(By.cssSelector("input#Selenium"));
        System.out.println(elements.size());
        Assert.assertEquals(elements.size(), 0);
        //...
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
