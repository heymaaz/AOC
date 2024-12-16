package TwentyFour.Day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    static List<Long> readFile(String path) {
        List<Long> out = new ArrayList<>();        
        File input = new File(path);
        try {
            Scanner sc = new Scanner(input);
            while(sc.hasNextLong()) {
                out.add(sc.nextLong());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            System.exit(0);
        }
        return out;
    }
}