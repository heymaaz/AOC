package TwentyFour.Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input {
    static String readFile(String path) {
        File input = new File(path);
        String t="";
        try {
            Scanner sc = new Scanner(input);
            while(sc.hasNextLine()) {
                t = sc.nextLine().trim();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            System.exit(0);
        }
        return t;
    }
}