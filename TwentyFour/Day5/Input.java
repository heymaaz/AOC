package TwentyFour.Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    static List<String> readFile(String path) {
        File input = new File(path);
        List<String> out = new ArrayList<>();
        try {
            Scanner sc = new Scanner(input);
            while(sc.hasNextLine()) {
                String t = sc.nextLine().trim();
                if(t.length()==0) {
                    out.add(0, Integer.toString(1+out.size()));
                    continue;
                }
                out.add(t);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            System.exit(0);
        }
        return out;
    }
}