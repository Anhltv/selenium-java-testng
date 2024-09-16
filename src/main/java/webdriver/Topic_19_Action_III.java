package webdriver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;

public class Topic_19_Action_III {

    //**
    // Những chức năng không nên áp dụng automation testing
    // Vì nó chạy không ổn định
    // Vì các tool/ framework chưa chính thức support
    // Vì mất nhiều time để implemen -> không mang lại nhiều value
    // Drag and drop (HTML5)
    // Captcha
    // SMS/ OTP/ Authentication Code
    // QR Code/ Bar Code
    // Game
    // Flash/ Flex
    // Google/ Facebook/ Twitter/.. -> UI (not API)
    // Đọc ghi file PDF
    // Compare image/ OCR (nhận diện ảnh)
    // Biometric (vân tay)
    // Face Recognite
    // Line/ Pie/ Bar/ Grant/ Chart (SVG)
    // Canvas
    // Audio/ Video Clip/ Streaming/ Live Streaming
    // Google Map
    // */

    WebDriver driver;

    Actions actions;

    String projectPath = System.getProperty("user.dir");

    JavascriptExecutor javascriptExecutor;

    @BeforeClass
    public void initialBrowser() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        actions = new Actions(driver);
        javascriptExecutor = (JavascriptExecutor) driver;

    }

    @Test
    public void TC_01_Drag_Drog_HTML4() throws InterruptedException {

        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));

        actions.dragAndDrop(sourceCircle, targetCircle).perform();

        Thread.sleep(3000);

        Assert.assertEquals(targetCircle.getText(), "You did great!");
        Assert.assertEquals(Color.fromString(targetCircle.getCssValue("background-color")).asHex().toUpperCase(), "#03A9F4");

    }

    @Test
    public void TC_02_Drag_Drog_HTML5() throws InterruptedException, IOException {

        // HTML 5 không support

        driver.get("https://automationfc.github.io/drag-drop-html5/");

//        WebElement sourceSquare= driver.findElement(By.cssSelector("div#column-a"));
//        WebElement targetSquare = driver.findElement(By.cssSelector("div#column-b"));
//
//        actions.dragAndDrop(sourceSquare, targetSquare).perform();
//
//        Thread.sleep(3000);
//
//        Assert.assertEquals(sourceSquare.getText(), "You did great!");
//        Assert.assertEquals(Color.fromString(targetSquare.getCssValue("background-color")).asHex().toUpperCase(), "#03A9F4");

        String jqueryDragDropContent = getContentFile(projectPath + "\\dragDrop\\dragAndDrop.js");

        // Drag A to B
        javascriptExecutor.executeScript(jqueryDragDropContent);
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");

        // Drag B to A

        javascriptExecutor.executeScript(jqueryDragDropContent);
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");

    }

    @Test
    public void TC_03_Drag_Drop_HTML5_Java_Robot() throws AWTException, InterruptedException {

        driver.get("https://automationfc.github.io/drag-drop-html5/");

        dragAndDropHTML5ByXpath("//div[@id='column-a']", "//div[@id='column-b']");

        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");

        dragAndDropHTML5ByXpath("div#column-a", "div#column-b");

        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");

    }

    @Test
    public void TC_04_Scroll_Element() throws InterruptedException {

        driver.get("http://live.techpanda.org/index.php/about-magento-demo-store/");

        Thread.sleep(3000);

        actions.scrollToElement(driver.findElement(By.cssSelector("input#newsletter"))).perform();

        Thread.sleep(3000);

    }

    @Test
    public void TC_05_() {

    }

    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.xpath(sourceLocator));
        WebElement target = driver.findElement(By.xpath(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
