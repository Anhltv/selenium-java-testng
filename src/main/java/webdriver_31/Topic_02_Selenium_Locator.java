package webdriver_31;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

    }

    @Test
    public void TC_01_ID() {
        driver.findElement(By.id("txtPhone"));
    }

    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("inputsearch2"));
    }

    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("txtEmail"));
    }

    @Test
    public void TC_04_Link() {
        driver.findElement(By.linkText("Đăng Ký"));
    }

    @Test
    public void TC_05_PartialLink() {
        driver.findElement(By.partialLinkText("Hướng dẫn sử dụng"));
        driver.findElement(By.partialLinkText("sử dụng"));
    }

    @Test
    public void TC_06_tagName() {
        int aNumber = driver.findElements(By.tagName("a")).size();
        System.out.println("Tổng số thẻ a = "+ aNumber);
    }

    @Test
    public void TC_07_CSS() {
        //CSS vs ID
        driver.findElement(By.cssSelector("input#txtFirstname"));
        driver.findElement(By.cssSelector("#txtEmail"));
        driver.findElement(By.cssSelector("input[id='txtCEmail']"));

        //CSS vs Class
        driver.findElement(By.cssSelector("input.inputsearch2"));
        driver.findElement(By.cssSelector(".inputsearch2"));
        driver.findElement(By.cssSelector("input[class='inputsearch2']"));

        //CSS vs Name
        driver.findElement(By.cssSelector("input[name='txtFirstname']"));
        driver.findElement(By.cssSelector("input[name='txtEmail']"));
        driver.findElement(By.cssSelector("input[name='txtCEmail']"));

        //CSS vs Link
        driver.findElement(By.cssSelector("a[href='https://alada.vn/tai-khoan/dang-ky.html']"));

        //CSS vs PartialLink
        driver.findElement(By.cssSelector("a[href^='https://alada.vn/']"));
        driver.findElement(By.cssSelector("a[href$='tai-khoan/dang-ky.html']"));

        //CSS vs tagName
        int aNumber = driver.findElements(By.cssSelector("a")).size();
        System.out.println("Tổng số thẻ a = " + aNumber);
    }

    @Test
    public void TC_08_Xpath() {
        //xpath vx ID
        driver.findElement(By.xpath("//input[@id='txtFirstname']"));
        driver.findElement(By.xpath("//input[@id='txtEmail']"));
        driver.findElement(By.xpath("//input[@id='txtCEmail']"));

        //xpath vs Class
        driver.findElement(By.xpath("//input[@class='inputsearch2']"));

        //xpath vs Name
        driver.findElement(By.xpath("//input[@name='txtFirstname']"));
        driver.findElement(By.xpath("//input[@name='txtEmail']"));
        driver.findElement(By.xpath("//input[@name='txtCEmail']"));

        //xpath vs Link
        driver.findElement(By.xpath("//a[text()='Đăng Ký']"));
        driver.findElement(By.xpath("//a[@href='https://alada.vn/huong-dan-thanh-toan.html']"));

        //xpath vs PartialLink
        driver.findElement(By.xpath("//a[starts-with(@href,'https://alada.vn/huong-dan-thanh')]"));
        driver.findElement(By.xpath("//a[contains(@href,'https://alada.vn/huong-dan-thanh')]"));

        //xpath vs tagName
        int aNumber = driver.findElements(By.xpath("//a")).size();
        System.out.println("Tổng số thẻ a = " + aNumber);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
