package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_23_Windows_Tab {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //**
        // Java
        // - Set - không cho phép lưu trùng
        // - List - cho phép lưu trùng
        // - Queue
        // - Map
        //
        // */

    }

    @Test
    public void TC_01_Github() throws InterruptedException {

        //**
        // 3 cái cần phải switch
        // - Alert
        // - Frame/ Iframe
        // - Windows Tab
        // - Switch vào rồi switch về
        // - 3 tab thì khả năng sẽ chạy fail - Fail khoảng
        // */

        driver.get("https://automationfc.github.io/basic-form/index.html");


        // Lấy ra ID của tab/ window mà drive đang active tại page đó
        String githubWindowID = driver.getWindowHandle();

        // Click vào Google link -> nó sẽ bật lên 1 tab mới và tự nhảy qua
        // Nhưng code về Selenium là driver không tự nhảy qua - nó vẫn ở tab cũ
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        Thread.sleep(2000);

        // Switch qua tab Google
        switchToWindowByID(githubWindowID);
        Thread.sleep(2000);

        String googleWindowId = driver.getWindowHandle();

        driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("Selenium Driver");
        Thread.sleep(2000);

        // Switch về tab Github
        switchToWindowByID(googleWindowId);
        Thread.sleep(2000);

        // Click vào Facebook link -> nó sẽ bật lên 1 tab mới và tự nhảy qua
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        Thread.sleep(2000);

        switchToWindowByTitle("Facebook – log in or sign up");

        // Quay về Github
        switchToWindowByTitle("Selenium WebDriver");

        // Click vào Tiki link -> nó sẽ bật lên 1 tab mới và tự nhảy qua
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        Thread.sleep(2000);

        switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        closeAllWindowWithoutParents(githubWindowID);

        // Sau khi chạy hết thì sẽ đóng hết các tab/ window ngoại trừ Github
        // Driver nó đang active/ đứng ở window/ tab nào ?



    }

    private void closeAllWindowWithoutParents(String githubWindowID) throws InterruptedException {
        // Lấy hết toàn bộ các ID của window/ tab
        Set<String> allWindowIDs = driver.getWindowHandles();

        // Dùng vòng lặp duyệt qua từng ID
        for (String id : allWindowIDs){
            if (!id.equals(githubWindowID)){
                driver.switchTo().window(id);
                Thread.sleep(2000);
                driver.close();
            }
        }

        // Switch vào window duy nhất còn lại
        driver.switchTo().window(githubWindowID);
    }

    private void switchToWindowByTitle(String expectedPageTitle) throws InterruptedException {
        // Lấy hết toàn bộ các ID của window/ tab
        Set<String> allWindowIDs = driver.getWindowHandles();

        // Dùng vong lặp duyệt qua từng ID
        for (String id: allWindowIDs){
            // Mỗi lần duyệt sẽ cho nó switch vào trước
            driver.switchTo().window(id);
            Thread.sleep(2000);

            // Get ra title của window/ tab hiện tại
            String pageTitle = driver.getTitle();

            // Kiểm tra title
            if (pageTitle.equals(expectedPageTitle)){
                break;
            }
        }
    }

    // Chỉ đúng với 2 cái Window/ Tab
    private void switchToWindowByID(String windowID) {
        // Lấy ra hết tất cả các ID của window/ tab hiện tại
        Set<String> allWindowIDs = driver.getWindowHandles();

        // Dùng vòng lặp để duyệt qua từng ID một
        for (String id: allWindowIDs){
            // Kiểm tra điều kiện: nếu ID nào mà khác với ID mong đợi thì switch qua
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
            }
        }
    }

    @Test
    public void TC_02_TechPanda() throws InterruptedException {

        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();

        // Click to Add to Compare button at Sony Xperia
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/" +
                "following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "The product Sony Xperia has been added to comparison list.");

        // Click to Add to Compare button at Samsung Galaxy
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/" +
                "following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.xpath("//button[@title='Compare']")).click();
        Thread.sleep(3000);

        switchToWindowByTitle("Products Comparison List - Magento Commerce");

        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/catalog/product_compare/index/");

        driver.findElement(By.xpath("//button[@title='Close Window']")).click();
        Thread.sleep(2000);

        switchToWindowByTitle("Mobile");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.switchTo().alert().getText(), "Are you sure you would like to remove all products from your comparison?");
        driver.switchTo().alert().accept();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The comparison list was cleared.");


    }

    @Test
    public void TC_03_Cambride() throws InterruptedException {

        driver.get("https://dictionary.cambridge.org/vi/");

//        driver.findElement(By.xpath("//button[@aria-label='Ok']")).click();

        driver.findElement(By.cssSelector("span.cdo-login-button")).click();
        Thread.sleep(2000);

        switchToWindowByTitle("Login");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("input[value='Log in']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='username']~span.gigya-error-msg-active")).getText()
                , "This field is required");

        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='password']~span.gigya-error-msg-active")).getText()
                , "This field is required");

        driver.close();
        Thread.sleep(2000);

        switchToWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("input#searchword")).sendKeys("code");

        driver.findElement(By.cssSelector("button[title='Tìm kiếm'")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#cald4-1~div span.headword>span")).getText(), "code");

    }
    @Test
    public void TC_04_Selenium_4x() throws InterruptedException {

        driver.get("http://live.techpanda.org/");

        // Hành vi này giống như Business/ End User thao tác
        // Khi nhảy qua tab mới thì tự switch luôn

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        Thread.sleep(2000);

        System.out.println("Drive ID = " + driver.toString());
        System.out.println("Window ID = " + driver.getWindowHandle());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        // Tự chủ động new tab hoặc new window
        driver.switchTo().newWindow(WindowType.TAB).get("http://live.techpanda.org/index.php/customer/account/");
        Thread.sleep(2000);

        System.out.println("Drive ID = " + driver.toString());
        System.out.println("Window ID = " + driver.getWindowHandle());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(), "This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(), "This is a required field.");

    }
    @Test
    public void TC_05_() {

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
