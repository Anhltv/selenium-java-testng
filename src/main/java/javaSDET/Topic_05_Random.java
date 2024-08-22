package javaSDET;

import org.testng.annotations.Test;

import java.util.Random;

public class Topic_05_Random {

    String prefixEmail = "jobiden";

    String postFixEmail = "@gmail.com";

    String fullEmail = prefixEmail + postFixEmail;

    @Test
    public void testEmail(){
        Random rand = new Random();

        // Local
        String fullEmail = prefixEmail + rand.nextInt(99999) + postFixEmail;

    }
}
