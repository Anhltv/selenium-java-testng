package BTVN;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_24_JavascriptExecutor {

    WebDriver driver;

    JavascriptExecutor jsExecutor;

    WebDriverWait webDriverWait;

    String email;

    @BeforeClass
    public void initialBrowser() {

        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        email = "lvananh672@gmail.com";

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));

    }

    @Test
    public void TC_01_Magento() throws InterruptedException {

        jsExecutor.executeScript("window.location = 'http://live.techpanda.org/'");
        webDriverWait.until(ExpectedConditions.urlMatches("http://live.techpanda.org/"));

//        webDriverWait.until(ExpectedConditions.jsReturnsValue("document.domain;"));
        String techPandaDomain = (String) jsExecutor.executeScript("return document.domain;");
        Thread.sleep(2000);
        Assert.assertEquals(techPandaDomain, "live.techpanda.org");

        String homePageURL = (String) jsExecutor.executeScript("return document.URL;");
        Assert.assertEquals(homePageURL, "http://live.techpanda.org/");

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Mobile']")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Mobile']")));

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div//button")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div//button")));
        Thread.sleep(3000);

        String samsungText = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
        Assert.assertTrue(samsungText.contains("Samsung Galaxy was added to your shopping cart."));

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Customer Service']")));
        Thread.sleep(3000);

        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("input#newsletter")));
        Thread.sleep(2000);
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + email + "');", driver.findElement(By.cssSelector("input#newsletter")));

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button[title='Subscribe'")));
        Thread.sleep(3000);

        String subscriptionText = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
        Assert.assertTrue(subscriptionText.contains("Thank you for your subscription."));

        jsExecutor.executeScript("window.location = 'https://www.facebook.com/'");

    }

    @Test
    public void TC_02_TechPanda() {

        navigateToUrlByJS("http://live.techpanda.org/");

        Assert.assertEquals(getDomain(), "live.techpanda.org");

        Assert.assertEquals(getPageURL(), "http://live.techpanda.org/");

        clickToElementByJS("//a[text()='Mobile']");
        clickToElementByJS("//a[@title='Samsung Galaxy']/following-sibling::div//button");

        Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

        clickToElementByJS("//a[text()='Customer Service']");

        scrollToElementOnTop("//input[@id='newsletter']");

        setAttributeInDOM("//input[@id='newsletter']", "value", email);

        clickToElementByJS("//button[@title='Subscribe']");

        Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));

        navigateToUrlByJS("https://www.facebook.com/");
    }

    @Test
    public void TC_03_Rode() throws InterruptedException {

        driver.get("https://warranty.rode.com/login");

        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        // Empty
        loginButton.click();

        String emptyEmailMessage = getElementValidationMessage("//input[@id='email']");
        Assert.assertEquals(emptyEmailMessage, "Please fill out this field.");

        // Email invalid
        String invalidEmailData = "aaa";
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmailData);
        loginButton.click();
        Thread.sleep(2000);

        String invalidEmailMessage = getElementValidationMessage("//input[@id='email']");

        if (driver.toString().contains("ChromeDriver")) {
            Assert.assertEquals(invalidEmailMessage, "Please include an '@' in the email address. '" + invalidEmailData + "' is missing an '@'.");
        } else {
            Assert.assertEquals(invalidEmailMessage, "Please enter an email address.");
        }

        invalidEmailData = "aaa@";
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmailData);
        loginButton.click();
        Thread.sleep(2000);

        invalidEmailMessage = getElementValidationMessage("//input[@id='email']");

        if (driver.toString().contains("")) {
            Assert.assertEquals(invalidEmailMessage, "Please enter a part following '@'. '" + invalidEmailData + "' is incomplete.");
        } else {
            Assert.assertEquals(invalidEmailMessage, "Please enter an email address.");
        }

        invalidEmailData = "aaa@aaa.";
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmailData);
        loginButton.click();
        Thread.sleep(2000);

        invalidEmailMessage = getElementValidationMessage("//input[@id='email']");
        String [] parts = invalidEmailData.split("@");

        if (driver.toString().contains("")) {
            Assert.assertEquals(invalidEmailMessage, "'.' is used at a wrong position in '" + parts[1] + "'.");
        } else {
            Assert.assertEquals(invalidEmailMessage, "Please enter an email address.");
        }

        // Email valid
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        loginButton.click();
        Thread.sleep(2000);

        Assert.assertEquals(getElementValidationMessage("//input[@id='password']"), "Please fill out this field.");

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

    public String getPageURL() {
        return (String) jsExecutor.executeScript("return document.URL;");
    }

    public String getDomain() {
        return (String) jsExecutor.executeScript("return document.domain;");
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
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getElement(locator));
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
