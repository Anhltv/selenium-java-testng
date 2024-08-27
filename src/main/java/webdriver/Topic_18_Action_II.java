package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_18_Action_II {

    WebDriver driver;

    Actions actions;

    String osName = System.getProperty("os.Name");

    Keys keys;

    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        actions = new Actions(driver);
//        if (osName.startsWith("Windows")){
//            keys = Keys.CONTROL;
//        } else {
//            keys = Keys.COMMAND;
//        }

    }

    @Test
    public void TC_01_Click_And_Hold_Block() throws InterruptedException {

        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
        Assert.assertEquals(allNumber.size(), 20);

        actions.clickAndHold(allNumber.get(0)) // Click vào số 1 và giữ chuột
                .moveToElement(allNumber.get(3)) // Di chuột tới số 4
                .release() // Nhả chuột trái ra - kết thúc cho sự kiện clickAndHold()
                .perform(); // Thực thi các câu lệnh trên (nếu không có thì câu lệnh không được thực hiện)
        Thread.sleep(2000);

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(), 4);
    }

    @Test
    public void TC_02_Click_And_Hold_Random() {

        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
        Assert.assertEquals(allNumber.size(), 20);

        // Nhấn phím Ctrl xuống (chưa nhả ra)
        actions.keyDown(Keys.CONTROL).perform();

        actions.click(allNumber.get(0))
                .click(allNumber.get(3))
                .click(allNumber.get(7))
                .click(allNumber.get(10))
                .click(allNumber.get(13))
                .click(allNumber.get(17))
                .perform();
        // 1 4 8 11 14 16 18

        // Nhả phím Ctrl ra
        actions.keyUp(Keys.CONTROL).perform();

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(), 6);

    }

    @Test
    public void TC_03_Double_Click() {

        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Tìm nút "Double click me"
        WebElement button = driver.findElement(By.xpath("//button[text()='Double click me']"));

        // Cuộn đến nút "Double click me"
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

        // Thực hiện double click
        actions.doubleClick(button).perform();

        // Kiểm tra kết quả sau khi double click
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");


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
