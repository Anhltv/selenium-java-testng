package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_20_Frame_IFrame {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {

        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void TC_01_() throws InterruptedException {

        //**
        // Case 1: Switch vào frame/ iframe như thế nào
        // Case 2: Đã switch rồi và muốn quay lại để thao tác với page bên ngoài thì sao
        // Case 3: Page A -> Switch vào iframe -> trong iframe
        // */

        // Trang HTML A
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        driver.findElement(By.cssSelector("img[alt='Campus Safety Survey']")).click();
        Thread.sleep(3000);

        // Switch qua iframe
        // Index: page hiện tại có nhiều iframe/ frame
        // Frame/ iframe đầu tiên sẽ có index = 0
        // Khi thêm mới/ update lại/ xóa bớt đi thì đổi index của các iframe
        // driver.switchTo().frame(0);

        // Id hoặc name (page có iframe/ frame có id/ name
        // driver.switchTo().frame("frame-one85593366");

        // WebElement ( có thể cover cả hai cách trên )
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));

        // Element thuộc trang HTML B
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("South Dorm");

        driver.findElement(By.xpath("//label[text()='Male']")).click();


        Thread.sleep(3000);

        // B quay lại A
        driver.switchTo().defaultContent();

        // driver đã quay lại A rồi
        driver.findElement(By.cssSelector("a.menu-item-login.fs-btn--transparent-kashmir")).click();

        driver.findElement(By.cssSelector("button#login")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(), "Username and password are both required.");

        // Từ A qua B
        driver.switchTo().frame(0);

        // Từ B qua C
        driver.switchTo().frame(0);

        // C quay lại B
        driver.switchTo().parentFrame();

        // B quay lại A
        driver.switchTo().defaultContent();

    }

    @Test
    public void TC_02_Iframe_ToiDiCodeDao() {

        driver.get("https://toidicodedao.com/");

        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb_iframe_widget iframe")));

        By followerText = By.xpath("//a[@title='Tôi đi code dạo']/parent::div/following-sibling::div[text()]");

        Assert.assertEquals(driver.findElement(followerText).getText(), "404,502 followers");

    }

    @Test
    public void TC_03_Frame_HDFC() throws InterruptedException {

        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        driver.switchTo().frame("login_page");

        driver.findElement(By.cssSelector("input[name='fldLoginUserId'")).sendKeys("automationfc");
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("a.login-btn")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("input[@id='keyboard]")).sendKeys("123456789");
        driver.findElement(By.cssSelector("a#loginBtn")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("p.error-msg")).getText(), "Customer ID/IPIN (Password) is invalid. Please try again.");
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
