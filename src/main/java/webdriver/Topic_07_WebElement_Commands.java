package webdriver;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_07_WebElement_Commands {

    WebDriver driver;
    WebElement element;

    @Test
    public void TC_01_WebElement(){

        // Dùng 1 lần
        driver.findElement(By.xpath("")).click(); //**

        element = driver.findElement(By.xpath(""));

        // Click vào các element dạng: button/ checkbox/ radio/ link/ image/ icon/..
        element.click();

        // Nhập liệu các element dạng: textbox/ textarea/ dropdown (edit)
        element.sendKeys("ella@kohyoung.com"); //**
        element.sendKeys(Keys.ENTER);
        // Dùng để xóa dữ liệu trước khi sendKeys
        element.clear(); //*

        driver.findElement(By.cssSelector("div.login-page"))
                .findElement(By.cssSelector("div.customer-blocks"))
                .findElement(By.id("Email"));
        driver.findElement(By.cssSelector("div.login-page div.customer-blocks input#Email")); //**

        // Submit Chỉ có tác dụng với form (Sign up/ Login/ Search/..)
        // Phải có thẻ form
        driver.findElement(By.id("Email")).sendKeys("");
        driver.findElement(By.id("Password")).sendKeys("");
        driver.findElement(By.id("Password")).submit();

        // Áp dụng cho tất cả các loại element
        // Kiểm tra 1 element có hiển thị hay không
        element.isDisplayed(); //**

        Assert.assertTrue(element.isDisplayed());
        Assert.assertFalse(element.isDisplayed());

        // Áp dụng cho duy nhất 3 loại: checkbox/ radio/ dropdown (default)
        // Kiểm tra 1 element đã được chọn rồi hay chưa chọn
        element.isSelected(); //*

        // Áp dụng cho tất cả các loại element
        // Kiểm tra 1 element có bị disable hay không (read-only)
        element.isEnabled();

        element.getCssValue("background-color");
        // #f82573
        // GUI: Font/ Size/ Color/ Position/ Location/..
        element.getCssValue("font-size"); //*

        // Áp dụng cho các element chứa text (Link/ Button/ Header/ Label/..)
        element.getText(); //**

        // Lấy ra giá trị của Attribute
        element.getAttribute("placeholder"); //**
        // Search store

        Dimension dimension = driver.manage().window().getSize();

        // Chiều rộng/ cao của element
        Dimension dimensionElement = element.getSize();

        Point pointBrowser = driver.manage().window().getPosition();

        // Vị trí của element so với viewport
        Point pointElement = element.getLocation();

        Rectangle rectangle = element.getRect();

        // Size
        rectangle.getWidth();
        rectangle.getHeight();
        rectangle.getDimension();

        // Location
        rectangle.getX();
        rectangle.getY();
        rectangle.getPoint();

        // Lấy ra cái thẻ html của element đó
        // Element A
        String tagname = driver.findElement(By.cssSelector("#FirstName")).getTagName();

        // Element B
        driver.findElement(By.xpath("//" + tagname + "[@id = 'LastName']"));

        // Lấy ra tên của element
        element.getAccessibleName();

        // Lấy ra loại element
        element.getAriaRole();

        // Lấy giá trị của thuộc tính DOM (Các thuộc tính này thường được sử dụng để lưu trữ dữ liệu trạng thái hoặc thông tin xác thực của một phần tử.)
        element.getDomAttribute("data-val-required");

        //  Lấy giá trị của một thuộc tính DOM chuẩn. Thuộc tính này thường là các thuộc tính có sẵn trong HTML và được định nghĩa theo chuẩn W3C.
        element.getDomProperty("formAction"); //*

        // Popup
        element.getShadowRoot(); //**

        // Sử dụng để chụp hình
        // Framework: HTML Report
        element.getScreenshotAs(OutputType.FILE); //*
        element.getScreenshotAs(OutputType.BYTES);
        element.getScreenshotAs(OutputType.BASE64); //*
    }

}
