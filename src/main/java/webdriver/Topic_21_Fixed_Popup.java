package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_21_Fixed_Popup {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void TC_01_NgoaiNgu24h() throws InterruptedException {

        driver.get("https://ngoaingu24h.vn/");

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        By loginPopup = By.xpath("//div[@role='dialog']");

        // Kiểm tra 1 element hiển thị có trong HTMl
        // Hiển thị trên UI -> true
        // Không hiển thị trên UI -> false
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.xpath("//input[@placeholder='Tài khoản đăng nhập']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//input[@placeholder='Mật khẩu']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//div[text()='Không có tài khoản?']/preceding-sibling::button")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Đăng nhập']/button")).isDisplayed());

        driver.findElement(By.xpath("//h2[text()='Đăng nhập']/button")).click();

    }

    @Test
    public void TC_02_Kyna() throws InterruptedException {

        driver.get("https://skills.kynaenglish.vn/dang-nhap");

        By loginPopup = By.cssSelector("div.right");

        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.id("user-login")).sendKeys("automation@gmail.com");
        driver.findElement(By.id("user-password")).sendKeys("123456789");
        driver.findElement(By.id("btn-submit-login")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.id("password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");

    }

    @Test
    public void TC_03_Tiki() throws InterruptedException {

        driver.get("https://tiki.vn/");

        // Popup 01 - Marketing
        // Hiển thị cố định khi vừa mở site ra
        // Khi đóng lại thì không còn trong HTML nữa
        // Khi refresh page thì lại hiện ra

        By marketingPopup = By.cssSelector("div#VIP_BUNDLE");

        // Kiểm tra hiền thị => Hiển thị cố định khu vừa mở site ra
        Assert.assertTrue(driver.findElement(marketingPopup).isDisplayed());

        driver.findElement(By.cssSelector("div#VIP_BUNDLE img[alt='close-icon")).click();
        Thread.sleep(3000);

        // Kiểm tra không hiển thị => Khi đóng lại thì không còn trong HTML nữa
        // Vì element không có trong HTML nên không thể chạy được step này
//        Assert.assertFalse(driver.findElement(marketingPopup).isDisplayed());

    }
    @Test
    public void TC_04_Tiki_NotInDom() throws InterruptedException {

        driver.get("https://tiki.vn/");

        driver.findElement(By.cssSelector("div#VIP_BUNDLE img[alt='close-icon")).click();

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        Thread.sleep(3000);

        By loginPopup = By.cssSelector("div.ReactModal__Content");

        // Popup hiển thị
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        // Kiểm tra element có trong HTML (present)
        // Không chắc chắn được là nó có hiển thị hay không
        Assert.assertEquals(driver.findElements(loginPopup).size(), 1);

        driver.findElement(By.cssSelector("p.login-with-email")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='show-pass']/parent::div/preceding-sibling::span")).getText()
                , "Email không được để trống");

        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='show-pass']/parent::div/following-sibling::span")).getText()
                , "Mật khẩu không được để trống");

        driver.findElement(By.cssSelector("img.close-img")).click();
        Thread.sleep(2000);

        // Popup không hiển thị (không còn trong DOM/ HTML)
        Assert.assertEquals(driver.findElements(loginPopup).size(), 0);

    }
    @Test
    public void TC_05_Facebook() throws InterruptedException {

        driver.get("https://www.facebook.com/");

        // findElement -> click()
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        Thread.sleep(3000);

        By signUpPopupBy = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");

        // Kiểm tra hiển thị
        Assert.assertTrue(driver.findElement(signUpPopupBy).isDisplayed());

        // Close đi
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        Thread.sleep(2000);

        // Kiểm tra không hiển thị
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(signUpPopupBy).size(), 0);

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
