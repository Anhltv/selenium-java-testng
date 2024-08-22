package javaSDET;

import java.util.ArrayList;
import java.util.List;

public class Topic_08_For {

    public static void main(String[] args) {
        // Biểu thức vong lặp (loop)
        int number = 100;

        // for (classic - iterator)
        for (int i = 0; i < number; i++){
            System.out.println(i);
        }

        // Collection: List/ Set/ Queue/ Map
        List<String> name = new ArrayList<String>();
        name.add("Manual Testing");
        name.add("Automation Testing");
        name.add("Regression Testing");
        name.add("UI Testing");
        name.add("API Testing");
        name.add("Mobile Testing");

        // for-each
        for (String a: name){
            System.out.println(a);
        }

        int i = 100;
        // while
        while (i<number){
            System.out.println(i);
            i++;
        }

        // do-while
        do { // Action trước
            System.out.println(i);
            i++;
        }while (i < number);

    }
}
