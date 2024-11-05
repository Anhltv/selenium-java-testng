package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_31_Wait_010_PageReady {

    WebDriver driver;

    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_AjaxLoading() {

        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        By selectedDateBy = By.cssSelector("span#ctl00_ContentPlaceholder1_Label1");

        Assert.assertEquals(driver.findElement(selectedDateBy).getText(), "No Selected Dates to display.");

        driver.findElement(By.xpath("//a[text()='12']")).click();

        // Wait Page Ready
        Assert.assertTrue(isPageLoadedSuccess());

        Assert.assertEquals(driver.findElement(selectedDateBy).getText(), "Tuesday, November 12, 2024");

    }

    @Test
    public void TC_02_Admin_NopCommerce() throws InterruptedException {

        driver.get("https://admin-demo.nopcommerce.com");

        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input#Password")).clear();
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.login-button")).click();
        Thread.sleep(2000);

        Assert.assertTrue(isPageLoadedSuccess());

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(@class,'fa-user)]/following-sibling::p"))).click();
//        driver.findElement(By.xpath("//i[contains(@class,'fa-user)]/following-sibling::p")).click();
        driver.findElement(By.xpath("//ul[contains(@style, 'display: block;')]//i[contains(@class,'fa-dot-circle')]/following-sibling::p[contains(string(),'Customers')]")).click();
        Assert.assertTrue(isPageLoadedSuccess());

        driver.findElement(By.xpath("//i[contains(@class,'fa-book)]/following-sibling::p")).click();
        driver.findElement(By.xpath("//ul[contains(@style, 'display: block;')]//i[contains(@class,'fa-dot-circle')]/following-sibling::p[contains(string(),'Products')]")).click();
        Assert.assertTrue(isPageLoadedSuccess());

    }

    @Test
    public void TC_03_OrangeHRM_API_Document(){

        driver.get("https://api.orangehrm.com/");

        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='spinner']"))));

        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='OrangeHRM REST API Documentation']")).isDisplayed());


    }

    public boolean isPageLoadedSuccess(){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // Điều kiện 1
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        // Điều kiện 2
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);

//        return new WebDriverWait(driver, Duration.ofSeconds(30)).until(new ExpectedCondition<Boolean>() {
//            @Override
//            public Boolean apply(WebDriver input) {
//                return (Boolean) ((JavascriptExecutor) driver).executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
//            }
//        });
    }

    public boolean waitAjaxLoadingInvisible(){
        return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("dix#ajaxBusy")));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
