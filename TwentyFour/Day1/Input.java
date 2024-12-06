package TwentyFour.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Input {
    static List<List<Integer>> readFile(String path) {
        File input = new File(path);
        List<Integer> out1 = new ArrayList<>();
        List<Integer> out2 = new ArrayList<>();
        try {
            Scanner sc = new Scanner(input);
            while(sc.hasNextInt()) {
                out1.add(sc.nextInt());
                out2.add(sc.nextInt());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            System.exit(0);
        }
        Collections.sort(out1);
        Collections.sort(out2);
        return List.of(out1, out2);
    }
}
