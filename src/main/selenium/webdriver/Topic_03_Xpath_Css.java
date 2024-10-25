package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_Xpath_Css {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.get("https://live.techpanda.org/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_() {

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
    }

    @Test
    public void TC_02_() {

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
