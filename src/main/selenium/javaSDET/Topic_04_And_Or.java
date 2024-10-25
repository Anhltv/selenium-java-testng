package javaSDET;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_04_And_Or {

    @Test
    public void verifyAnd() {

        String contactInformation = "Lê Thị V Anh\n" +
                "                    lvananh6723543@gmail.com";

        Assert.assertTrue(contactInformation.contains("Lê Thị V Anh")
                && contactInformation.contains("lvananh6723543@gmail.com"));

    }

}
