package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

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

    WebDriver driver; // null

    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_JQuery() throws InterruptedException {

        //**
        // Wait
        // If
        // For
        // List
        // Break
        // *//

        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

        selectItemInCustomDropdown("span#speed-button", "ul#speed-menu>li>div", "Slower");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slower");

        selectItemInCustomDropdown("span#number-button", "ul#number-menu>li>div", "10");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "10");

        selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu>li>div", "Dr.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Dr.");


    }

    // Dự án thức tế: 1 hàm đ thao tác lên dropdown chỉ dùng 1 site/app
    // Không dùng cho nhiều application khác nhau
    // Cơ chế của dropdown giống nhau
    @Test
    public void TC_02_React() throws InterruptedException {

        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemInCustomDropdown("div.dropdown", "div.item>span", "Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");

        selectItemInCustomDropdown("div.dropdown", "div.item>span", "Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");

    }

    @Test
    public void TC_03_VueJS() throws InterruptedException {

        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        selectItemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu>li>a", "Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

        selectItemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu>li>a", "Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");

        selectItemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu>li>a", "First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");

    }
    @Test
    public void TC_04_Editable() throws InterruptedException {

        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        enterItemCustomDropdown("input.search","div.item>span","Albania");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Albania");

        enterItemCustomDropdown("input.search","div.item>span","American Samoa");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "American Samoa");

    }
    @Test
    public void TC_05_() {

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

    private void selectItemInCustomDropdown(String parentCss, String childCss, String textItem) throws InterruptedException {

        //**
        // Hành vi để thao tác lên Dropdown
        // 1 - Chờ cho dropdown có thể thao tác lên được (clickable)
        // 2 - Click vào element nào đ nó xổ ra cái dropdown ra
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentCss))).click();
        Thread.sleep(2000);

        // 3 - Chờ cho tất cả các item được load ra (presence)
        // 4 - Tìm các item nào đúng với mong đợi
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));

        // 5 - Items
        for (WebElement item : allItems){

            System.out.println("----------" + item.getText() + "--------");
            if (item.getText().equals(textItem)){
                // 5 - Click lên item đó
                item.click();
                break;
            }
        }
        // *//

    }

    private void enterItemCustomDropdown(String parentCss, String childCss, String textItem) throws InterruptedException {

        //**
        // Hành vi để thao tác lên Dropdown
        // 1 - Chờ cho dropdown có thể thao tác lên được (clickable)
        // 2 - Sendkey vào dropdown
        WebElement dropdowmTextBox = explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentCss)));
        dropdowmTextBox.clear();
        dropdowmTextBox.sendKeys(textItem);
        Thread.sleep(2000);

        // 3 - Chờ cho tất cả các item được load ra (presence)
        // 4 - Tìm các item nào đúng với mong đợi
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));

        // 5 - Items
        for (WebElement item : allItems){

            System.out.println("----------" + item.getText() + "--------");
            if (item.getText().equals(textItem)){
                // 5 - Click lên item đó
                item.click();
                break;
            }
        }
        // *//

    }
}
