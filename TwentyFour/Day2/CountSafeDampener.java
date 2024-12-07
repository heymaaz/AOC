package TwentyFour.Day2;

import java.util.ArrayList;
import java.util.List;

public class CountSafeDampener {
    public static void main(String[] args) {
        List<List<Integer>> input = Input.readFile("TwentyFour/Day2/input.txt");
        int count = 0;
        for(List<Integer> a: input) {
            if(isSafeWithDampener(a)) {
                count++;
            }

        }
        System.out.println(count);
    }
    static boolean isSafeWithDampener(List<Integer> a) {
        if(CountSafe.isSafe(a))
            return true;
        
        for(int i = 0; i<a.size(); i++) {
            List<Integer> b = new ArrayList<>(a);
            b.remove(i);
            if(CountSafe.isSafe(b))
                return true;
        }
        return false;
    }
}
