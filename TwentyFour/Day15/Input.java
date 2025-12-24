package TwentyFour.Day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input {
    static String readFile(String path) {
        StringBuffer sb = new StringBuffer();
        File input = new File(path);
        try {
            Scanner sc = new Scanner(input);
            while(sc.hasNextLine()) {
                String t = sc.nextLine().trim();
                sb.append("\n").append(t);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            System.exit(0);
        }
        return sb.toString().trim();
    }
}