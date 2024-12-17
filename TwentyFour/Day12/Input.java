package TwentyFour.Day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    static List<List<Character>> readFile(String path) {
        List<List<Character>> out = new ArrayList<>();        
        File input = new File(path);
        try {
            Scanner sc = new Scanner(input);
            while(sc.hasNextLine()) {
                String t = sc.nextLine();
                
                List<Character> temp = new ArrayList<>();
                for( char c:t.toCharArray()) {
                    temp.add(c);
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