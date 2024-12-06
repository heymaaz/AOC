package TwentyFour.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

class Distance {
    public static void main(String[] args) {
        List<List<Integer>> input = readFile();
        int distance = 0;
        for(int i = 0; i<input.get(0).size(); i++) {
            int temp = input.get(0).get(i)-input.get(1).get(i);
            distance += Math.abs(temp);
            // System.out.println(input.get(0).get(i)+"\t"+input.get(1).get(i)+"\t"+temp);
        }
        System.out.println(distance);
    }

    static List<List<Integer>> readFile() {
        File input = new File("TwentyFour/Day1/input.txt");
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