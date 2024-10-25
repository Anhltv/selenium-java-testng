package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Annotations {

    @BeforeClass
    public void beforeClass(){
        System.out.println("Before Class");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("After Class");
    }

    @Test
    public void TC_01_() {
        System.out.println("Test 01");
    }

    @Test
    public void TC_02_() {
        System.out.println("Test 02");
    }

    @Test
    public void TC_03_() {
        System.out.println("Test 03");
    }

}
