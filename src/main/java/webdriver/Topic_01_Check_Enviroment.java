package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_01_Check_Enviroment {

    WebDriver driver;

    @Test
    public void TC_01_Run_On_Firefox(){
        driver = new FirefoxDriver();
        driver.get("https://vi-vn.facebook.com/");
        driver.quit();
    }

    @Test
    public void TC_02_Run_On_Chrome(){
        driver = new ChromeDriver();
        driver.get("https://vi-vn.facebook.com/");
        driver.quit();
    }

    @Test
    public void TC_03_Run_On_Egde(){
        driver = new EdgeDriver();
        driver.get("https://vi-vn.facebook.com/");
        driver.quit();
    }

    //**
    // Thực thi 1 testcase manual về mặt cơ bản
    // 1. Setup: OS/Browser/Web/Page/Data
    // 2. Action/Excute: Tương tác lên element nào/ nhập liệu/ verify/...
    // 3. Clean: Delete data test/ account/ browser/...
    // *//

    //**
    // Java testing framework
    // 1. JUnit ( Unit test + API )
    // 2. TestNG ( UI )
    // 3. AssertJ
    // 4. Hamcrest
    // 5. Mockito
    // 6. PowerMock
    // *//
}
