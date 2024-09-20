package javaSDET_31;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Topic_01_Data_Type {
    /*
     kiểu dữ liệu nguyên thủy (Primitive Type)
     8 loại
     Số nguyên: byte - short - int - long
     Không có phần thập phân: nhân viên trong 1 công ty/ học sinh trong 1 lớp/ trường/...
    */

    byte bNumber = 10;
    short sNumber = 100;
    int iNumber = 1000;
    long lNumber = 10000;

    /*
    Số thực: float - double
    Có phần thập phân: điểm số/ lương/...
     */
    float fNumber = 9.9f;

    double dNUmber = 10.15d;

    //ký tự: char
    char k = ' ';
    char c = 'M';

    // logic: boolean (luận lý)
    // kết quả bài test: pass/ fail
    boolean status = true;

    // Kiểu dữ liệu tham chiếu Reference Type
    // Array (kiểu nguyên thủy/ tham chiếu) - Khai báo cố định, không tăng giảm số lượng
    int[] studenAge = {15, 20, 8};
    String[] studentName = {"Nguyễn Văn A", "Lê Thị B"};

    // Object (đại diện cho các kiểu dữ liệu khác)
    // Đối tượng => chuyển đổi qua các kiểu dữ liệu khác
    Object studentAddress = "123 ABC street";
    Object studentAge = 35;
    Object employeeGender = false;

    // String - Chuỗi ký tự
    String name = "Automation";
    String employeeNumber = "123456789";

    // Class
    FirefoxDriver firefoxDriver = new FirefoxDriver();
    Topic_01_Data_Type topic01 = new Topic_01_Data_Type();
    // public class FirefoxDriver

    // Interface
    WebDriver driver = new ChromeDriver();
    // public interface WebDriver
    JavascriptExecutor jsExecutor;

    // Collection: List / Set/ Queue - Khai báo động, tăng giảm theo thực tế sử dụng
    List<String> studentAddressList = new ArrayList<>();
    List<String> studentCity = new LinkedList<>();
    List<String> studentPhone = new Vector<>();

    ArrayList<String> studentCityArray = new ArrayList<String>();

    // Khai báo 1 biến để lưu trữ 1 loại dữ liệu nào đó
    // Access Modifier: Phạm vi truy cập (public/ private/ protected/ default)
    /*
    Kiểu dũ liệu
    Tên biến
    Giá trị của biến - gán vào vs phép =
    Nếu như không gán: dữ liệu mặc định =0 hoặc bằng null
     */
    public int studentNumber = 200;
}
