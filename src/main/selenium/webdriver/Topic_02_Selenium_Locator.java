package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
    
    WebDriver driver;

    String fullName = "Selenium Locator";

//    WebDriver secondDriver;

    @BeforeClass
    public void initialBrowser() {

        // Mở browser lên
        driver = new EdgeDriver();
        // Mở web lên
        driver.get("https://demo.nopcommerce.com/register");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    public void Example(){

        // Tương tác lên Email Address textbox

        // HTML Source Code
        // Thẻ - Thuộc tính - Giá trị thuộc tính
        // Tagname - Atrribute - Value

        // Xpath format: //tagname[@attribute='value']

        // Css format: tagname[attribute='value']

        // Tương tác lên Email Address textbox
        // 8 loại locator để tìm

        // Tìm 1 element
        WebElement emailTextbox = driver.findElement(By.id(""));
        emailTextbox.click();
        emailTextbox.clear();
        emailTextbox.sendKeys();

        // Tìm nhiều element giống nhau
        List<WebElement> textboxs = driver.findElements(By.cssSelector(""));

        // 1 - Thao tác lên luôn (dùng 1 lần)
        driver.findElement(By.id("")).getText();

        // 2 - Lưu dữ liệu lại (dùng nhiều lần)
        driver.findElement(By.id(""));

    }

    @Test
    public void TC_01_ID() throws InterruptedException {

        driver.findElement(By.id("small-searchterms"));
        driver.findElement(By.id("FirstName"));

    }

    @Test
    public void TC_02_Class() throws InterruptedException {

        // Nó không lấy hết toàn bộ giá trị (nếu có khoảng trắng)
        driver.findElement(By.className("register-next-step-button"));

    }

    @Test
    public void TC_03_Name() throws InterruptedException {

        driver.findElement(By.name("DateOfBirthDay"));
        driver.findElement(By.name("DateOfBirthMonth"));
        driver.findElement(By.name("DateOfBirthYear"));

    }

    @Test
    public void TC_04_LinkText() {
        // Chỉ làm việc với element là link và có text
        // Thẻ a và có thuộc tính href
        // Phải lấy hết toàn bộ text không chừa cái nào hết (tuyệt đối)
        driver.findElement(By.linkText("Register"));
        driver.findElement(By.linkText("Log in"));
        driver.findElement(By.linkText("Wishlist"));
    }
    @Test
    public void TC_05_Partial_Link_Text() {
        // Chỉ làm việc với element là link
        // Có thể lấy hết toàn bộ text hoặc 1 phần (hay dùng)
        driver.findElement(By.partialLinkText("Register"));
        driver.findElement(By.partialLinkText("Digital"));
        driver.findElement(By.partialLinkText("downloads"));

    }

    @Test
    public void TC_06_Tagname() {

        // Tên thẻ (HTML)
        // Tìm tất cả các element giống nhau (thẻ của component giống nhau)
        // Tất cả các textbox/ checkbox/ radio/ link/ button/...
        driver.findElements(By.tagName("button"));
        driver.findElements(By.tagName("input"));
        driver.findElements(By.tagName("label"));

    }

    @Test
    public void TC_07_Css() {

        // Css với id
        driver.findElement(By.cssSelector("input#Company"));
        driver.findElement(By.cssSelector("#Company"));
        driver.findElement(By.cssSelector("input[id='Company']"));

        // Css với class
        driver.findElement(By.cssSelector("button.register-next-step-button"));
        driver.findElement(By.cssSelector("button[class='button-1 register-next-step-button']"));

        // Css với name
        driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"));
        driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"));
        driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"));

        // Css với link text
        driver.findElement(By.cssSelector("a[href='/register?returnUrl=%2Fregister']"));
        driver.findElement(By.cssSelector("a[href='/login?returnUrl=%2Fregister']"));

        // Css với partial link text
        driver.findElement(By.cssSelector("a[href*='register?']"));
        driver.findElement(By.cssSelector("a[href*='login?']"));

        // Css với tagname
        driver.findElement(By.cssSelector("a"));
        driver.findElement(By.cssSelector("button"));
        driver.findElement(By.cssSelector("input"));

    }

    @Test
    public void TC_08_XPath() {

        // Xpath với id
        driver.findElement(By.xpath("//input[@id='small-searchterms']"));
        driver.findElement(By.xpath("//input[@id='Password']"));
        driver.findElement(By.xpath("//input[@id='Company']"));

        // Xpath với class
        driver.findElement(By.xpath("//button[@class='button-1 register-next-step-button']"));
        // Có thể lấy toàn bộ hoặc lấy 1 phần
        driver.findElement(By.xpath("//button[contains(@class,'register-next-step-button')]"));

        // Xpath với name
        driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"));
        driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"));
        driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"));

        // Xpath với link text
        driver.findElement(By.xpath("//a[text()='Register']"));
        driver.findElement(By.xpath("//a[text()='Log in']"));
        driver.findElement(By.xpath("//a[text()='Shipping & returns']"));

        // Xpath với partial link text
        driver.findElement(By.xpath("//a[contains(text(),'Register')]"));
        driver.findElement(By.xpath("//a[contains(text(),'Log in'])"));
        driver.findElement(By.xpath("//a[contains(text(),'& returns']"));

        //*[id="newsletter"] * đại diện cho bất kì 1 thẻ nào - những sẽ chạy chậm vì phải quét tất cả các thẻ
        // 1 - Tìm tất cả các thẻ có id = "newsletter"
        // Selenium khi thao tác với 1 element thì trường hợp truyền vào 1 locator không phải là duy nhất ( > 1 )
        // thì khi thao tác lên sẽ lấy element đầu tiên để thao tác

        // Các bước tìm locator
        // 1- Locator phải tối ưu/ duy nhất
        // 2- Đang đứng tại đúng màn hình này
        // 3- Page đã được load xong
        // 4- Đã áp dụng wait hay chưa
        // 5- Viết đúng cú pháp/ convention của ngôn ngữ/ thư viện đó


    }

    @Test
    public void TC_09_Relative_Locator() {

        // Element A
        By passwordTextBoxBy = By.cssSelector("input#Password");
        WebElement passwordTextBox = driver.findElement(By.cssSelector("input#Password"));

        // Element B
        By rememberMeCheckboxBy = By.id("RememberMe");

        // Element C
        By forgotPasswordLinkBy = By.cssSelector("span.forgot-password");

        // Element D
        By loginButtonBy = By.cssSelector("button.login-button");

        // Element E
        WebElement rememberMeLabelText = driver.findElement(RelativeLocator.with(By.tagName("label"))
                .above(loginButtonBy) // Label đang nằm trên login button
                .below(passwordTextBox) // Label đang nằm dưới Password textbox
                .toRightOf(rememberMeCheckboxBy) // Label đang nằm bên phải so với Rememberme checkbox
                .toLeftOf(forgotPasswordLinkBy) // Label đang nằm bên trái so với Forgot Password link
        );

        // 1 - Khi không thể định danh được element của chính nó (dựa vào những cía vị trí bên cạnh/ gần đó)
        // 2 - Sử dụng để test GUI (giao diện - position khớp với Design)

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
