package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Topic_31_Explicit_Ajax {

    WebDriver driver;

    WebDriverWait explicitWait;

    String projectPath = System.getProperty("user.dir") + File.separator + "\\uploadFiles\\";
    String image1 = "Image1.jpg";
    String image2 = "Image2.jpg";
    String image3 = "Image3.jpg";

    String image1Path = projectPath + image1;
    String image2Path = projectPath + image2;
    String image3Path = projectPath + image3;

    @BeforeClass
    public void initialBrowser(){

        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Calendar(){
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        // Verify Calendar element is displayed
        Assert.assertTrue(explicitWait.until(ExpectedConditions.
                visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1"))).isDisplayed());

        // Wait and verify text
//        Assert.assertTrue(explicitWait.until(ExpectedConditions.
//                textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), "No Selected Dates to display.")));

        // Find element and verify text
        WebElement selectedDate = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
        Assert.assertEquals(selectedDate.getText(), "No Selected Dates to display.");


        // Wait and click to element
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()='23']"))).click();

        // Wait end verify ajax loading invisible
//        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id$='RadCalendar1']>div.raDiv"))));

        selectedDate = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));

        Assert.assertEquals(selectedDate.getText(), "Wednesday, October 23, 2024");

        // Wait and verify text
//        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), "Wednesday, October 23, 2024")));
    }

    @Test
    public void TC_02_Upload() throws InterruptedException {

        driver.get("https://gofile.io/?t=uploadFiles");

        // Wait cho loading icon ở màn hình Upload không còn hiển thị
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));

        // Wait và click cho Upload File button được click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-secondary"))).click();

        // Wait cho loading icon ở màn hình tiếp theo không còn hiển thị
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));

        By inputBy = By.xpath("//input[@type='file']");

        driver.findElement(inputBy).sendKeys(image1Path + "\n" + image2Path + "\n" + image3Path);
        Thread.sleep(2000);

        // Wait cho loading icon ở màn hình load file lên
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#mainUpload div.spinner-border"))));

        // Wait cho các progress bar của các file biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-bar"))));

        // Wait và kiểm tra text hiển thị
        Assert.assertTrue(explicitWait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//div[@id='mainUpload']//div[text()='Your files have been successfully uploaded']"))).isDisplayed());

        // Wait và click vào link
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#mainUpload div>a.ajaxLink"))).click();

        // Wait cho loading icon ở màn hình Upload không còn hiển thị
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
