package BTVN;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_24_JavascriptExecutor {

    WebDriver driver;
    JavascriptExecutor jsExecutor;

    WebDriverWait webDriverWait;

    @BeforeClass
    public void initialBrowser() {

        driver = new EdgeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));

    }

    @Test
    public void TC_01_Magento() {

        jsExecutor.executeScript("window.location = 'http://live.techpanda.org/'");

//        webDriverWait.until(ExpectedConditions.jsReturnsValue("document.domain;"));
        String techPandaDomain = (String) jsExecutor.executeScript("return document.domain;");
        Assert.assertEquals(techPandaDomain, "live.techpanda.org");

        String homePageURL = (String) jsExecutor.executeScript("return document.URL;");
        Assert.assertEquals(homePageURL, "http://live.techpanda.org/");

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Mobile'")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Mobile'")));


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
