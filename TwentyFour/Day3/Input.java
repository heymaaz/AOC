package TwentyFour.Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input {
    static String readFile(String path) {
        File input = new File(path);
        String out = "";
        try {
            Scanner sc = new Scanner(input);
            while(sc.hasNextLine()) {
                out=out+"\n"+(sc.nextLine().trim());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            System.exit(0);
        }
        return out;
    }
}
