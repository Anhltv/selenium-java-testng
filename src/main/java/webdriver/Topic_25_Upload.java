package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_25_Upload {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir") + File.separator + "\\uploadFiles\\";
    String image1 = "Image1.jpg";
    String image2 = "Image2.jpg";
    String image3 = "Image3.jpg";

    String image1Path = projectPath + image1;
    String image2Path = projectPath + image2;
    String image3Path = projectPath + image3;

    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void TC_01_Single_File() throws InterruptedException {

        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By inputBy = By.xpath("//input[@type='file']");

        // Load file lên - 1 lần load 1 file
        driver.findElement(inputBy).sendKeys(image1Path);
        Thread.sleep(2000);

        driver.findElement(inputBy).sendKeys(image2Path);
        Thread.sleep(2000);

        driver.findElement(inputBy).sendKeys(image3Path);
        Thread.sleep(2000);

        // Verify file được load lên
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image3 + "']")).isDisplayed());

        // Click upload cho từng file
        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

        for (WebElement startButton : startButtons) {
            startButton.click();
            Thread.sleep(2000);
        }

        // Verify các file được upload thành công
        Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + image1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + image2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + image3 + "']")).isDisplayed());

    }

    @Test
    public void TC_02_Multiple_File() throws InterruptedException {

        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By inputBy = By.xpath("//input[@type='file']");

        // Load file lên - 1 lần load nhiều file
        driver.findElement(inputBy).sendKeys(image1Path + "\n" + image2Path + "\n" + image3Path);
        Thread.sleep(2000);

        // Verify file được load lên
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image3 + "']")).isDisplayed());

        // Click upload cho từng file
        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

        for (WebElement startButton : startButtons) {
            startButton.click();
            Thread.sleep(2000);
        }

        // Verify các file được upload thành công
        Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + image1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + image2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + image3 + "']")).isDisplayed());

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
