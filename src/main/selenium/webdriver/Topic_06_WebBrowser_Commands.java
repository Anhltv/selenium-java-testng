package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() throws MalformedURLException {

        // Run with browser (local)
        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver = new EdgeDriver();
        driver = new SafariDriver();
        driver = new InternetExplorerDriver();

        // Remote (Grid/ Docker/ Cloud Testing)
        // Networking (LAN/ WAN/ IP/ Port)
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);

    }

    @Test
    public void TC_01_() throws MalformedURLException {

        // Dùng để mở ra 1 Url bất kỳ ( lưu ý phải bắt đầu bằng http/ https)
        String homePageUrl = "https://demo.nopcomerce.com";
        driver.get(homePageUrl);

        driver.get("https://demo.nopcomerce.com"); //**

        // Đóng browser ( active tab/ window)
        driver.close(); //*

        // Đóng browser (bao gồm tất cả các tab/ window)
        driver.quit(); //**

        //Lấy ra title của page hiện tại
        // 1 - Lưu giữ liệu lại rồi kiểm tra sau
        String homePageTitle = driver.getTitle();
        Assert.assertEquals(homePageTitle, "nopComerce demo store");

        // 2 - Kiểm tra trực tiếp
        Assert.assertEquals(driver.getTitle(), "nopComerce demo store");

        // Lấy ra URL của page hiện tại
        driver.getCurrentUrl();

        // Lấy ra ID của tab/ window đang active
        driver.getWindowHandle();

        // Lấy ra tất cả ID của tất cả các tab/ window đang có
        driver.getWindowHandles();

        // Đi tìm 1 element
        driver.findElement(By.xpath("")); //**

        // Đi tìm n element
        driver.findElements(By.xpath("")); //**

        WebDriver.Options options = driver.manage();

        // Selenium ver 4
        // Dùng để chờ cho việc tìm element (findElement/ findElements)
        options.timeouts().implicitlyWait(Duration.ofSeconds(15)); //**

        // Selenium ver 3
        options.timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        WebDriver.Timeouts timeouts = driver.manage().timeouts();
        // Dùng để chờ cho việc page được load xong
        timeouts.pageLoadTimeout(Duration.ofSeconds(15));

        //Dùng để chờ một đoạn scripts được thực thi xong
        // JavascriptExcutor - js
        timeouts.scriptTimeout(Duration.ofSeconds(15));

        WebDriver.Window window = driver.manage().window();
        // Thu nhỏ về Taskbar để chạy
        window.minimize();

        // Phóng to lên (vẫn còn taskbar)
        window.maximize(); //*

        // Phóng to lên (không còn taskbar)
        window.fullscreen();


        window.setSize(new Dimension(1920, 1080));
        window.getSize();

        window.setPosition(new Point(0, 0));
        window.getPosition();

        // Test GUI: Graphic User Interface
        // Font/ Color/ Size/ Position/ Location...

        // Responsive
        driver.manage().window().setSize(new Dimension(1366, 768));

        Dimension dimension = driver.manage().window().getSize();

        driver.manage().window().setPosition(new Point(0, 0));

        driver.manage().window().getPosition();

        // Lấy hết tất cả các Cookie: Test Class 01 (Register tài khoản - lưu cookie lại)
        Set<Cookie> cookies = driver.manage().getCookies(); //*

        // Lấy cookie theo tên
        driver.manage().getCookieNamed(".Nop.Antiforgery");

        // Xóa hết cookie
        driver.manage().deleteAllCookies();

        for (Cookie cookie : cookies) {
            // Xóa cookie theo thứ tự
            driver.manage().deleteCookie(cookie);
        }

        // Xóa cookie theo tên
        driver.manage().deleteCookieNamed(".Nop.Antiforgery");

        // Đến 1 Test Class khác 02/03/04.. (Không cần login - set cookie đã có vào đây rồi refresh lại)
        for (Cookie cookie : cookies) {
            // Add cookie theo thứ tự
            driver.manage().addCookie(cookie);
        }

        driver.navigate().refresh(); // Login thành công

        Logs log = driver.manage().logs();
        LogEntries logEntries = log.get("BROWSER");

        for (LogEntry logEn : logEntries) {
            System.out.println(logEn);
        }

        log.getAvailableLogTypes();

        WebDriver.Navigation navigation = driver.navigate();

        // Refresh/ F5
        navigation.refresh();

        // Quay lại trang trước đó
        navigation.back();

        // Chuyển tiếp tới trang trước đó
        navigation.forward();

        // Mở Url bất kỳ
        navigation.to("");

        navigation.to(new URL(""));

        // Alert/ Iframe/ Windows (Tab)
        WebDriver.TargetLocator targetLocator = driver.switchTo();

        // Alert
        targetLocator.alert().accept(); //*
        targetLocator.alert().dismiss(); //*

        // Frame/ Iframe
        targetLocator.frame(""); //*
        targetLocator.defaultContent(); //*

        // Windows
        targetLocator.window(""); //*

        // Lấy ra ID của tab/window đang active
        driver.getWindowHandle(); //*

        // Lấy ra tất cả ID của tất cả các tab/ window đang có
        driver.getWindowHandles(); //*


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
