package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_24_JavascriptExcutor {

    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");

    }

    @Test
    public void TC_01_() {

        // Lấy ra cái title của browser
        driver.getTitle();

        // Lấy ra cái URL
        driver.getCurrentUrl();

        // Click vào một cái element mà đang bị che/ ẩn bằng Web Element
        driver.findElement(By.cssSelector("ul.top-menu.notmobile a[href='/desktops")).click();

        // Click vào một element bằng JSExecutor nó chẳng quan tâm ẩn/ hiện
        // Sai hành vi của End User => Giả lập lại hành vi giống như End User đang thao tác trên application
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("ul.top-menu.notmobile a[href='/desktops")));

        // Lấy ra domain
        jsExecutor.executeScript("document.domain");
        System.out.println(jsExecutor.executeScript("return document.domain;"));

        // Lấy ra một Web Element
        WebElement emailTextbox = (WebElement) jsExecutor.executeScript("return document.querySelector('input#Email')");
        emailTextbox.sendKeys("automation@gamil.com");

        WebElement passwordTextBox = driver.findElement(By.cssSelector("input#Password"));

        String loginPageUrl = (String) jsExecutor.executeScript("return document.URL;");

        jsExecutor.executeScript("document.qurySelectorAll(\"input[type='email']\");");

        // Scroll



    }

    @Test
    public void TC_02_WebBrowser() {

        // Refresh browser
        jsExecutor.executeScript("history.go(0)");

        // Get inner text of page
        String sText = jsExecutor.executeScript("return document.documentElement.innerText;").toString();

        // Get title of page
        String pageTitle = jsExecutor.executeScript("return document.title;").toString();

        // Get URL page
        String URL = (String) jsExecutor.executeScript("return document.URL");

        // Get domain page
        String domain = (String) jsExecutor.executeScript("return document.domain");

        // Scroll to 50 pixel
        jsExecutor.executeScript("window.scrollBy(0,50)");

        // Scroll to bottom page
        jsExecutor.executeScript("window.scrollBy(0, document.body.scrollHeight)");

        // Scroll down to pixel number
        jsExecutor.executeScript("window.scrollBy(0," + "range" + ")", "");

        // Open any URL
        jsExecutor.executeScript("window.location = 'https://demo.nopcommerce.com/'");

    }

    @Test
    public void TC_03_WebElement() {

        // Click an element
        WebElement element = driver.findElement(By.xpath("//abcdxyz"));
        jsExecutor.executeScript("arguments[0].click();", element);

        // Scroll to element
        // - true: above element
        // - false: bellow element
        WebElement element1 = driver.findElement(By.id("id_of_element"));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element1);

        // Hightlight element
        jsExecutor.executeScript("arguments[0].style.border='2px groove green'", element1);

        // Remove an Attribute
        jsExecutor.executeScript("arguments[0].removeAttribute('disable');", element1);

        // Get HTML5 validation message
        jsExecutor.executeScript("return arguments[0].validationMessage;", element1);

    }
    @Test
    public void TC_04_() {

    }
    @Test
    public void TC_05_() {

    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
