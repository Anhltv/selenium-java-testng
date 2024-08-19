package javaSDET;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.*;

public class Topic_01_DataType {

    // 2 nhóm kiểu dữ liệu trong java

    // Cách khai báo:
    // Access Modifier: phạm vi truy cập (private/ public/ protected/ default)
    // C1: Acess Modifier - Kiểu dữ liệu - Tên biến - Giá trị của biến
    public char cName = 'b';

    // C2:
    // 2.1: Access Modifier - Kiểu dữ liệu - Tên biến
    private char cAddress;

    // 2.2: Tên biến - Giá trị gán sau (Hàm)
    public void clickToElement(){
        char cAddress = 'c';
    }


    // Nhóm 1 - Kiểu dữ liệu nguyên thủy
    // char: kiểu ký tự (character)
    // Khi gán (khởi tạo) giá trị thì nằm trong dấu nháy đơn (')
    char cZip = 'b';

    // byte/ short/ int/ long: số nguyên
    // Khi gán (khởi tạo) giá trị thì không nằm trong dấu nào hết
    byte bNumber = -120;

    short sNumber = 1200;

    int iNumber = 350000;

    long lNumber = 900000;

    // float/ double: số thực
    // Khi gán (khởi tạo) giá trị thì không nằm trong dấu nào hết
    float fNumber = 15.7F;

    double dNumber = 15.925D;

    // boolean: logic
    // Khi gán (khởi tạo) giá trị thì không nằm trong dấu nào hết
    boolean gender = false;

    // Nhóm 2 - Kiểu dữ liệu tham chiếu
    // String: Chuỗi
    // Khi gán (khởi tạo) giá trị thì nằm trong dấu (")

    // Class
    FirefoxDriver firefoxDriver = new FirefoxDriver();
    Actions actions = new Actions(firefoxDriver);

    Topic_01_DataType topic01DataType = new Topic_01_DataType();

    // Interface
    WebDriver webDriver;
    JavascriptExecutor javascriptExecutor;

    // Array
    String[] studentName = {"Lê", "Thị", "Vân", "Anh"};

    Integer[] studentPhone = {9877864, 89767643, 62367445};

    // List/ Set/ Queue
    List<String> studentAddress = new ArrayList<String>();
    List<String> studentCity = new LinkedList<String>();

    // Map
    Map<String, Integer> zip = new HashMap<>();
    // Convention: Quy ước khi lập trình
    // Tên biến/ tên hàm: viết dưới dạng camel case
    // Chữ cái đầu tiên luôn viết thường
    // name/ address/ city/ phone/ zipCode




}
