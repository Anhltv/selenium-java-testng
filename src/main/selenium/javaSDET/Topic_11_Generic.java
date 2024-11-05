package javaSDET;

import java.util.ArrayList;
import java.util.List;

public class Topic_11_Generic {

    public static void main(String[] args) {

        // List chỉ chứa kiểu dữ liệu String
        // E T V K L: the type of elements in this list
        List<String> students = new ArrayList<String>();
        students.add("Nguyen");
        students.add("Tien");
        students.add("Dai");

        //non-Generic
        List address = new ArrayList<>();
        address.add("123 Main ST");
        address.add(15);
        address.add(true);
        address.add(15.56);



    }

}
