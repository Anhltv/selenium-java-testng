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

public class Topic_26_Wait_Element_Status {

    WebDriver driver;

    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }

    @Test
    public void TC_01_Visible() {

        driver.get("https://www.facebook.com/");

        // 1 - Element thỏa mãn điều kiện wait ngay lập tức
        // 2 - Vào wait nhưng chưa thỏa mãn
        // - Chờ tiếp và lặp lại điều kiện và tìm thấy thì quay lại giống 1
        // + Implicit
        // + WebDriverWait
        // + FluentWait


        // 1 - Element có trên UI và có trong cây HTML
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

    }

    @Test
    public void TC_02_Invisible() {

        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

        // Wait cho Email Address textbox được visible
//        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email__']")));

        // 2 - Element không có trên UI nhưng vẫn có trong cây HTML
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

        // 3 - Element không có trên UI và không có trong HTML
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

    }

    @Test
    public void TC_03_Presence() {

        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

        // Wait cho Email Address textbox được visible
//        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email__']")));

        // Điều kiện mồi để Confirm Email được xuất hiện
        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("dam@gmail.com");

        // 1 - Element có trên UI và có trong cây HTML
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

        // Điều kiện mồi để Confirm Email không còn xuất hiện
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();

        // 2 - Element không có trên UI nhưng vẫn có trong cây HTML
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

    }

    @Test
    public void TC_04_Staleness() {

        driver.get("https://www.facebook.com/");

        // Click mở popup ra
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

        // Tại thời điểm này Confirm Email đang có trong HTML
        WebElement confirmEmail = driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']"));

        // Click đóng popup lại
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

        // 3 - Element không có trên UI và không có trong HTML
        explicitWait.until(ExpectedConditions.stalenessOf(confirmEmail));

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

    }

    @Test
    public void TC_05_() {

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
