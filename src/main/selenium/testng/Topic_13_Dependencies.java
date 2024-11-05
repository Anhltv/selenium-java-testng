package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

@Listeners(listeners.ExtentReport.class)
public class Topic_13_Dependencies {

    WebDriver driver;

    // dependsOnMethods dùng để set độ phụ thuộc của cái test case với nhau ( ví dụ testcase số 2 muốn chạy được thì phải chạy thành công testcase số 1 )
    // Test và tái sử dụng giữ liệu test cho nhiều chức năng liên tiếp
    // Evidence: Log/ Exception/ Screenshot/ Video

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_CreateNewUser(){
//        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "TC_01_CreateNewUser")
    public void TC_02_ViewAndSearchUser(){

    }

    @Test(dependsOnMethods = "TC_01_CreateNewUser")
    public void TC_03_UpdateExistingUser(){

        Assert.assertTrue(false);

    }

    @Test(dependsOnMethods = "TC_03_UpdateExistingUser")
    public void TC_04_MovExistingUserToOtherRole(){

    }

    @Test(dependsOnMethods = "TC_04_MovExistingUserToOtherRole")
    public void TC_05_DeleteExistingUser(){

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

}
