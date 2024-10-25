package webdriver;

import com.beust.ah.A;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_11_Default_Dropdown {

    WebDriver driver;

    Select select;

    Actions actions;

    WebDriverWait explicitWait;

    JavascriptExecutor javascriptExecutor;

    String fullName;

    Random rand;

    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();

        // Nhận driver là tham số
        actions = new Actions(driver);

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        javascriptExecutor = (JavascriptExecutor) driver;
    }

//    @Test
    public void TC_01_Facebook_SignUp() {

        driver.get("https://www.facebook.com/rep/");

        // Dropdown xuất hiện
        select = new Select(driver.findElement(By.cssSelector("select#day")));

        fullName = "Automation Testing";

        // Đọc code có biết nó là tỉnh nào không ?
        // Index thay đổi vị trí
        // Chạy fail -> Reproduce lại -> 20 -> Manual Test

        // 1 - Index
        // Index thay đổi vị trí
        // Đọc code có biết nó là tỉnh nào không ? => Chạy fail -> Reproduce lại -> 20 -> Manual Test

//        select.selectByValue("9433");
        // 2 - Value
        // Option không bắt buộc phải có attribute calue
        // Đọc code có biết nó là tỉnh nào không => Chạy fail -> Reproduce lại -> 20 -> Manual Test
        // Thêm 1 số bươc để đi tìm nó là cái gì/ ở đâu

//        select.selectByVisibleText("thành phố Hà Nội");
        // Giống nhưu End User chọn
        // Không bị trùng dữ liệu/ không để trống dữ liệu
        // Không thay đổi text nếu đổi vị trí
        // Chạy fail -> Reproduce lại -> 20 -> Manual Test => Ít mất time hơn

        // Chọn item
        select.selectByVisibleText("25");

        // Chọn xong verify đã chọn thành công hay chưa
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "25");

        // Verify cái dropdown có phải là multiple select hya không
        // Nếu là multiple -> trả về là true
        // Nếu là single -> trả về là false
        Assert.assertFalse(select.isMultiple());

        // Verify xem tổng số lượng item trong dropdown này là bao nhiêu?
        Assert.assertEquals(select.getOptions().size(), 31);

        select = new Select(driver.findElement(By.cssSelector("select#month")));
        select.selectByVisibleText("Jun");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Jun");

        select = new Select(driver.findElement(By.cssSelector("select#year")));
        select.selectByVisibleText("2006");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "2006");

    }

    @Test
    public void TC_02_() {

    }

    @Test
    public void TC_03_() {

    }
//    @Test
    public void TC_04_Nopcommerce() throws InterruptedException {

        String firstName, lastName, emailAddress, companyName, password, date, month, year;

        firstName = "Reinaldos";
        lastName = "Kehri";
        emailAddress = "rkehri" + rand.nextInt(999) + "@utexas.edu";
        companyName = "Miboo";
        password = "sN2&3c$UE|3V.+3";
        date = "23";
        month = "November";
        year = "2003";

        driver.get("https://demo.nopcommerce.com/");

        Thread.sleep(5000);

        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);

        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).selectByVisibleText(date);
        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).selectByVisibleText(month);
        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).selectByVisibleText(year);

        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Company")).sendKeys(companyName);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);

        driver.findElement(By.cssSelector("button#register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.registration-result-page div.result")).getText(), "Your registration completed");

        driver.findElement(By.cssSelector("a.ico-login")).click();

        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("btn.login-button")).click();

        driver.findElement(By.cssSelector("a.ico-account")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), lastName);

        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay'"))).getFirstSelectedOption().getText(), date);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth'"))).getFirstSelectedOption().getText(), month);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear'"))).getFirstSelectedOption().getText(), year);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), emailAddress);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"), companyName);


    }

//    @Test
    public void TC_05_Rode() {

        driver.get("https://www.rode.com/wheretobuy");

        new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Vietnam");
        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("HO CHI MINH");
        driver.findElement(By.cssSelector("button.btn-default")).click();

        List<WebElement> dealers = driver.findElements(By.xpath("//h3[text()='Dealers']/following-sibling::div//h4"));
        Assert.assertEquals(dealers.size(), 16);

        for (WebElement deElement : dealers){
            System.out.println(deElement.getText());
        }

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
