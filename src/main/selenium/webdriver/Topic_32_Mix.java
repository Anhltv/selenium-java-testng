package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_32_Mix {

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();

    }

    @Test
    public void TC_01_Element_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        // Wait vs Explicit
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

        // Wait vs Implicit
        driver.findElement(By.cssSelector("input#email"));

    }

    @Test
    public void TC_02_Element_Not_Found_Only_Implicit() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        // Wait vs Implicit
        driver.findElement(By.cssSelector("input#automation"));

        // 3 - Nếu không tìm thấy element nào
        // Mới đầu vào findElement nhưng không thấy:
        // Tìm lại mà thấy element thì không cần chờ hết tổng time còn lại
        // Tìm lại và không thấy hết tổng time 13s thì đánh fail testcase
        // Show lỗi: NoSuchElementException

    }

    @Test
    public void TC_03_Element_Not_Found_Only_Explicit_By() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        By emailTextboxBy = By.cssSelector("input#automationfc");

        // Wait vs Explicit
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailTextboxBy));
    }

    @Test
    public void TC_03_Element_Not_Found_Only_Explicit_WenElement() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        WebElement emailTextbox = driver.findElement(By.cssSelector("input#automationfc"));

        // Wait vs Explicit
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));
    }

    @Test
    public void TC_04_Element_Not_Found_Only_Explicit() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        System.out.println("Start = " + getDateTimeNow());
        // Wait vs Explicit
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));
        } catch (Exception e) {
            System.out.println("End = " + getDateTimeNow());
            throw new RuntimeException(e);
        }
    }

    public String getDateTimeNow(){
        Date date = new Date();
        return date.toString();
    }



    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
