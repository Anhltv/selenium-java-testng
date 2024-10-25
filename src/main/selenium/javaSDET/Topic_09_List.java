package javaSDET;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class Topic_09_List {

    public static void main(String[] args) {
        // Java Collection

        RemoteWebDriver driver; // Cha

        driver = new FirefoxDriver(); // Con
        driver = new ChromeDriver(); // Con
        driver = new EdgeDriver(); // Con

        FirefoxDriver firefoxDriver = new FirefoxDriver();

        ArrayList<String> address = new ArrayList<>();
        address.add("Ho Chi Minh");
        address.add("Đồng Nai");

        Vector<Float> studentPoint = new Vector<>();

        LinkedList<Integer> studentAge = new LinkedList<>();

        Stack<Boolean> status = new Stack<>();

        List<String> studentName = new Stack<>();


    }

}
