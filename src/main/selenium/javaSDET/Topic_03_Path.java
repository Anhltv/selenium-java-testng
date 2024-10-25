package javaSDET;

import java.io.File;

public class Topic_03_Path {

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);

        String osName = System.getProperty("os.name");
        System.out.println(osName);

        String separator = File.separator;
        System.out.println(separator);
    }

}
