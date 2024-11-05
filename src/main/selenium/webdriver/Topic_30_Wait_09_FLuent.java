package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_30_Wait_09_FLuent {

    WebDriver driver;

    WebDriverWait explicitWait;

    FluentWait<WebDriver> fluentDriver;

    FluentWait<WebElement> fluentElement;

    FluentWait<String> fluentString;

    private long fullTimeoutInSecond = 30;

    private long pollingTimeoutInMilisecond = 300;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        fluentDriver = new FluentWait<WebDriver>(driver);

        // Time - Default Polling Time: 0.5s
        // explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Time - Polling
        // explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));
    }

    @Test
    public void TC_01_LyThuyet() {

        fluentDriver = new FluentWait<WebDriver>(driver);

        fluentElement = new FluentWait<WebElement>(driver.findElement(By.cssSelector("")));

        fluentString = new FluentWait<String>("Hello world!");

        // Tong time
        fluentDriver.withTimeout(Duration.ofSeconds(10));

        // Polling time
        fluentDriver.withTimeout(Duration.ofSeconds(300));

        // Ignore NoSuchElement exceptions
        fluentDriver.ignoring(NoSuchElementException.class);

        // CONDITION
        fluentDriver.until(new Function<WebDriver, Object>() { // new Function<T, V>
            @Override
            public Object apply(WebDriver webDriver) {
                return null;
            }
        });

        fluentDriver.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver webDriver) {
                return webDriver.findElement(By.cssSelector("")).getText();
            }
        });

        fluentDriver.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(400))
                .ignoring(NoSuchElementException.class, TimeoutException.class)
                .until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        return webDriver.findElement(By.cssSelector("")).isDisplayed();
                    }
                });
    }

    @Test
    public void TC_02_() {

        driver.get("https://automationfc.github.io/dynamic-loading/");

        waitAndFindElement(By.cssSelector("div#start>button")).click();

        // Cho HelloWord text hiển th trong xong 10s

        // Setting
//        fluentDriver.withTimeout(Duration.ofSeconds(10))
//                .pollingEvery(Duration.ofMillis(100))
//                .ignoring(NoSuchElementException.class);

        // Conditon
//        fluentDriver.until(new Function<WebDriver, Boolean>() {
//
//            @Override
//            public Boolean apply(WebDriver webDriver) {
//                return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
//            }
//        });

        // Condition
        String helloText = waitAndFindElement(By.xpath("//div[@id='finish']/h4")).getText();
//        String helloText = fluentDriver.until(new Function<WebDriver, String>() {
//            @Override
//            public String apply(WebDriver webDriver) {
//                String text = driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();
//                System.out.println("Get text = " + text);
//                return text;
//            }
//        });

        Assert.assertEquals(helloText, "Hello World!");

//        fluentDriver.until(new Function<WebDriver, Alert>() {
//            @Override
//            public Alert apply(WebDriver webDriver) {
//                return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());
//            }
//        });
    }

    @Test
    public void TC_03_() {

        driver.get("https://automationfc.github.io/fluent-wait/");

        WebElement countDownTime = driver.findElement(By.cssSelector("div#javascript_countdown_time"));

        fluentElement = new FluentWait<WebElement>(countDownTime);

        fluentElement.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        fluentElement.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                String text = webElement.getText();
                System.out.println(text);
                return text.endsWith("00");
            }
        });

    }

    public WebElement waitAndFindElement(By locator) {
        FluentWait<WebDriver> fluentDriver = new FluentWait<WebDriver>(driver);
        fluentDriver.withTimeout(Duration.ofSeconds(fullTimeoutInSecond))
                .pollingEvery(Duration.ofSeconds(pollingTimeoutInMilisecond))
                .ignoring(NoSuchElementException.class);

        return fluentDriver.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(locator);
            }
        });
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
