package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Custom_Dropdown {

    //**
    // Hành vi giống nhau
    // Khác nhau bởi HTML
    // Select -> Option -> Thư viện Select của Selenium nó support
    // Thẻ khác Select -> Option: Không support nên tự viết hàm để xử lý
    //
    // *//

    //**
    // Riêng Selenium có 3 cách để wait + 1 cách của Java Thread Sleep()
    // + Selenium
    // ImplicitWait - wait cho việc tìm element, áp dụng duy nhất cho 2 hàm: findElement()/ findElements()
    // WebDriveWait = ExplicitWait - áp dụng cho element với 1 điều kiện rõ ràng ( Hiển thị/ Không hiển thị/ Presence = xuất hiện trong HTML/ Clickable/ Selected)
    // FluentWait - có thể sửa thời gian polling lại được
    // + Java Thread
    // Thread.sleep(5000)
    // *//

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/");

    }

    @Test
    public void TC_01_() {

        //**
        // Hành vi để thao tác lên Dropdown
        // 1 - Chờ cho dropdown có thể thao tác lên được (clickable)
        // 2 - Click vào element nào đ nó xổ ra cái dropdown ra
        // 3 - Chờ cho tất cả các item được load ra
        // 4 - Tìm các item nào đúng với mong đợi
        // 5 - Click lên item đó
        // *//

        //**
        // Wait
        // If
        // For
        // List
        // Break
        // *//

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
