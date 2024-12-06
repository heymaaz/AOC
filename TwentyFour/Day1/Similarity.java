package TwentyFour.Day1;

import java.util.HashMap;
import java.util.List;

public class Similarity {
    public static void main(String[] args) {
        List<List<Integer>> input = Input.readFile("TwentyFour/Day1/input.txt");

        long similarity = 0;
        HashMap<Integer,Integer> count = new HashMap<>();
        for(int a : input.get(1)) {
            count.put(a, count.getOrDefault(a,0)+1);
        }

        for(int a : input.get(0)) {
            long temp = a*count.getOrDefault(a,0);
            similarity+=temp;
        }
        System.out.println(similarity);
    }
}
