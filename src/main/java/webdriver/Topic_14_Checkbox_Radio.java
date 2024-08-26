package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_14_Checkbox_Radio {

    //**
    // Dropdown
    // Default 1 loại thẻ (select/ option)
    // Custom: thẻ khác div/ ul/ li/ span/...
    // *//

    // Check box với radio là thẻ span

    WebDriver driver;

    JavascriptExecutor javascriptExecutor;

    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();

        javascriptExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        // Set kích thước của browser là 1366x768
//        driver.manage().window().setSize(new Dimension(1366, 768));

        //**
        // Các tool support test
        // Test Studio
        // Test Complete
        // Ranorex
        // UFT
        // Test Architect
        // Katalon
        // Smartbear
        // *//

    }

    @Test
    public void TC_01_Telerik() {

        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        // Verify checkbox/ radio is enabled/ disabled
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isEnabled());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isEnabled());

        // Verify checkbox/ radio is selected/ deselected
        // Element HTML: input
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isSelected());

        // Click to "Dual-zone air conditioning" checkbox
        String dualZoneAirCheckbox = "//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input";

        // Scroll xuống thêm 1 đoạn
//        ((JavascriptExecutor) driver).executeScript("window.srollBy(0,300)");

        // Nếu như chưa chọn thì mới click - Muốn chọn
        if (!driver.findElement(By.xpath(dualZoneAirCheckbox)).isSelected()) {
            driver.findElement(By.xpath(dualZoneAirCheckbox)).click();
        }
        Assert.assertTrue(driver.findElement(By.xpath(dualZoneAirCheckbox)).isSelected());

        // De-select to "Dual-zone air conditioning" checkbox - Bỏ chọn
        if (driver.findElement(By.xpath(dualZoneAirCheckbox)).isSelected()) {
            driver.findElement(By.xpath(dualZoneAirCheckbox)).click();
        }
        Assert.assertFalse(driver.findElement(By.xpath(dualZoneAirCheckbox)).isSelected());

        By twoPetroBy = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");

//        if (!driver.findElement(twoPetroBy).isSelected()){
//            driver.findElement(twoPetroBy).click();
//        }
//
//        Assert.assertTrue(driver.findElement(twoPetroBy).isSelected());
    }

    @Test
    public void TC_02_Multiple() {

        driver.get("https://automationfc.github.io/multiple-fields/");

        // Select all checkboxes
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("span.form-checkbox-item>input"));

        // Click all checkboxes
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }

        // Verify all checkboxes selected
        for (WebElement checkbox : checkboxes) {
            Assert.assertTrue(checkbox.isSelected());
        }

        // Deselect all checkboxes
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
        }

        // Verify all checkboxes selected
        for (WebElement checkbox : checkboxes) {
            Assert.assertFalse(checkbox.isSelected());
        }

        // Select 1 in all + verify
        driver.findElement(By.cssSelector("input[value='Venereal Disease']")).click();
        driver.findElement(By.cssSelector("input[value='High Blood Pressure']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Venereal Disease']")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("input[value='High Blood Pressure']")).isSelected());

        // Select 1 in all + verify
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected() && checkbox.getAttribute("value").equals("Heart Attack")) {
                checkbox.click();
            }
        }

        Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Heart Attack']")).isSelected());
    }

    @Test
    public void TC_03_Ubuntu() {

        //**
        // Hệ điều hành Ubuntu
        //*/

        driver.get("https://login.ubuntu.com/");

        // Thẻ input: dùng để click
        // Dùng để verify: isSelected()

        By newUserRadio = By.cssSelector("input#id_new_user");

        // 1 - Dùng thẻ input để click -> Lỗi
        // Dùng thẻ input này để verify -> Pass
        // driver.findElement(By.cssSelector("input#id_new_user")).click();
        Assert.assertFalse(driver.findElement(newUserRadio).isSelected());

        newUserRadio = By.cssSelector("label.new-user");

        // 2 - Dùng 1 thẻ khác input để click -> Pass
        // Dùng thẻ đó để verify -> Fail
        // isSelected() -> Dùng cho thẻ input/ option
        driver.findElement(newUserRadio).click();
        Assert.assertFalse(driver.findElement(newUserRadio).isSelected());

        // 3 - Dùng 1 thẻ khác input để click
        // Dùng thẻ input này để verify -> Pass
        // By newUserRadioLabel = By.cssSelector("label.new-user");
        By newUserRadioInput = By.cssSelector("input#id_new_user");

        // driver.findElement(newUserRadioLabel).click();
        // Assert.assertTrue(driver.findElement(newUserRadioInput).isSelected());

        javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(newUserRadioInput));
        Assert.assertTrue(driver.findElement(newUserRadioInput).isSelected());

        By termCheckbox = By.cssSelector("input#id_accept_tos");
        javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(termCheckbox));
        Assert.assertTrue(driver.findElement(termCheckbox).isSelected());

    }

    @Test
    public void TC_04_GGDocx() throws InterruptedException {

        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        Thread.sleep(5000);

        By hcmRadio = By.xpath("//div[@aria-label='Hồ Chí Minh']");

        driver.findElement(hcmRadio).click();

        // Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Hồ Chí Minh' and @aria-checked='true']")).isDisplayed());
        Assert.assertEquals(driver.findElement(hcmRadio).getAttribute("aria-checked"), "true");

        By quangNoodleCheckBox = By.xpath("//div[@aria-label='Mì Quảng']");

        // Check
        if (driver.findElement(quangNoodleCheckBox).getAttribute("aria-checked").equals("false")) {
            driver.findElement(quangNoodleCheckBox).click();
        }

        Assert.assertEquals(driver.findElement(quangNoodleCheckBox).getAttribute("aria-checked"), "true");

        // Uncheck
        if (driver.findElement(quangNoodleCheckBox).getAttribute("aria-checked").equals("true")) {
            driver.findElement(quangNoodleCheckBox).click();
        }

        Assert.assertEquals(driver.findElement(quangNoodleCheckBox).getAttribute("aria-checked"), "false");

    }

    @Test
    public void TC_05_() {

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
