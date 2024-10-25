package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_13_Button {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //**
        // Đối với button chỉ cần verify
        // Click
        // Disabled/ Enabled
        // Background màu gì, maybe verify text
        // *//

    }

    @Test
    public void TC_01_Fashaha() {

        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        By loginButton = By.cssSelector("button.fhs-btn-login");

        // isEnabled nếu element mà nó enabled thì trả về true/ disable trả về false
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());

        String loginBackgroundColor = driver.findElement(loginButton).getCssValue("background-color");
        Color loginColor = Color.fromString(loginBackgroundColor);
        Assert.assertEquals(loginColor.asHex().toUpperCase(), "#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("dao@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");

        // Mong đợi nó là enabled
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());

        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(), "#C92127");

        driver.findElement(loginButton).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.fhs-login-msg")).getText(), "Số điện thoại/Email hoặc Mật khẩu sai!");

        Assert.assertEquals(driver.findElement(loginButton).getText(), "Đăng nhập");

        WebElement loginButtonElement = driver.findElement(loginButton);

        String loginButtonRGBA = loginButtonElement.getCssValue("background-color");
        // RGBA
        Color loginButtonColor = Color.fromString(loginButtonRGBA);

        String loginButtonHexa = loginButtonColor.asHex();

        String loginButtonHexaUpperCase = loginButtonHexa.toUpperCase();

    }

    @Test
    public void TC_02_Ranorex() {

        driver.get("https://play.goconsensus.com/u5d5156df");

        Assert.assertFalse(driver.findElement(By.cssSelector("button[data-testid='lead form continue']")).isEnabled());

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
