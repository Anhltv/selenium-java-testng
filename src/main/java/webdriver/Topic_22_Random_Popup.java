package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_22_Random_Popup {

    //**
    // Fixed popup
    // Hiển thị hoặc không hiển thị theo ý muốn của User được
    // 1 trường hợp: hiển thị hoặc không hiển thị đều theo yêu cầu/ business -> mở hoặc đóng theo yêu cầu của mình
    // Random popup
    // Trường hợp 1: Nó sẽ luôn hiển thị - mình có thể đóng và action tiếp
    // Trường hợp 2: Nó không hiển thị luôn
    // User không có quyền để mở popup lên - setting mở popup là từ business của app/ system
    // */

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void TC_01_Java_Code_Geeks() throws InterruptedException {

        driver.get("https://www.javacodegeeks.com/");

        By newsletterPopupBy = By.xpath("//div[@data-title='Newsletter Free eBooks'" +
                "and not (contains(@style, 'display:none'))]");

        // Hiển thị thì close đi rồi action tiếp
        if (driver.findElements(newsletterPopupBy).size() > 0 && driver.findElements(newsletterPopupBy).get(0).isDisplayed()){
            System.out.println("----------------GO TO IF---------------");
            driver.findElement(By.xpath("//div[@data-title='Newsletter Free eBooks'" +
                    "and not (contains(@style, 'display:none'))]//a[contains(@onclick, 'lepopup_close')]")).click();
            Thread.sleep(2000);

        }

        // Không hiển thị thì action tiếp
        System.out.println("----------------IGNORE IF---------------");
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile");
        driver.findElement(By.cssSelector("form#search span.tie-search-icon")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("header>h1.page-title")).isDisplayed());


    }

    @Test
    public void TC_02_VNK() throws InterruptedException {

        driver.get("https://vnk.edu.vn/");

        By marketingPopupBy = By.cssSelector("div.popmake-content");

        // Hiển thị thì close đi rồi action tiếp
        if (driver.findElements(marketingPopupBy).size() > 0 && driver.findElements(marketingPopupBy).get(0).isDisplayed()){
            System.out.println("----------------GO TO IF---------------");
            driver.findElement(By.xpath("div.popmake-content~button")).click();
            Thread.sleep(2000);

        }

        // Không hiển thị thì action tiếp
        System.out.println("----------------IGNORE IF---------------");
        driver.findElement(By.xpath("//ul[@id='mega-menu-primary']//a[text()='Liên hệ']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div.title-content>h1")).isDisplayed());

    }

    @Test
    public void TC_03_Dehieu() throws InterruptedException {

        driver.get("https://dehieu.vn/");

        By contentPopupBy = By.cssSelector("div.modal-content");

        // Hiển thị thì close đi rồi action tiếp
        if (driver.findElements(contentPopupBy).size() > 0 && driver.findElements(contentPopupBy).get(0).isDisplayed()){
            System.out.println("----------------GO TO IF---------------");
            driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
            Thread.sleep(2000);

        }

        // Không hiển thị thì action tiếp
        System.out.println("----------------IGNORE IF---------------");
        driver.findElement(By.cssSelector("input.search-form")).sendKeys("Khóa học Lập dự toán M&E");

        Assert.assertEquals(driver.findElement(By.cssSelector("div.course-item-detail a")).getAttribute("title"), "Khóa học Lập dự toán M&E");

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
