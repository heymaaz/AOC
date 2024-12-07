package TwentyFour.Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Input {
    static List<List<Integer>> readFile(String path) {
        File input = new File(path);
        List<List<Integer>> out = new ArrayList<>();
        try {
            Scanner sc = new Scanner(input);
            while(sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                List<Integer> temp = Arrays.stream(line.split(" "))
                                            .map(Integer::parseInt)
                                            .collect(Collectors.toList());
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
