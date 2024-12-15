package TwentyFour.Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    static List<List<Character>> readFile(String path) {
        File input = new File(path);
        List<List<Character>> out = new ArrayList<>();
        try {
            Scanner sc = new Scanner(input);
            while(sc.hasNextLine()) {
                String str = sc.nextLine();
                List<Character> b = new ArrayList<>();
                for(char c :str.toCharArray()) {
                    b.add(c);
                }
                out.add(b);
                // out.add(new ArrayList<>(
                //     sc.nextLine()
                //     .trim()
                //     .toUpperCase()
                //     .chars()
                //     .mapToObj(ch -> (char) ch)
                //     .collect(Collectors.toList())
                // ));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            System.exit(0);
        }
        return out;
    }
}