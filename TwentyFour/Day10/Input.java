package TwentyFour.Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    static List<List<Integer>> readFile(String path) {
        List<List<Integer>> out = new ArrayList<>();        
        File input = new File(path);
        try {
            Scanner sc = new Scanner(input);
            while(sc.hasNextLine()) {
                String t = sc.nextLine().trim();

                List<Integer> temp = new ArrayList<>();
                for(char c:t.toCharArray()) {
                    temp.add(c-'0');
                }
                out.add(temp);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            System.exit(0);
        }
        return out;
    }
}