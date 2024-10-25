package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Priority {

    @BeforeClass(alwaysRun = true)
    public void init(){
        System.out.println("Pre-Condition for bellow testcase");
    }

    @Test(priority = 1)
    public void Priority_01_SearchWithDate(){

    }

    @Test(priority = 2)
    public void Priority_02_SearchWithBilling(){

    }

    @Test(priority = 3)
    public void Priority_03_SearchWithProduct(){

    }

    @AfterClass(alwaysRun = true)
    public void after(){
        System.out.println("Post-Condition for above testcases");
    }

}
