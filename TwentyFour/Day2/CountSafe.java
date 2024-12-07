package TwentyFour.Day2;

import java.util.List;

public class CountSafe {
    public static void main(String[] args) {
        List<List<Integer>> input = Input.readFile("TwentyFour/Day2/input.txt");
        int count = 0;
        for(List<Integer> a: input) {
            if(isSafe(a)) {
                count++;
            }
        }
        System.out.println(count);
    }
    static boolean isSafe(List<Integer> a) {
        if (a.size() <= 1) {
            return true;
        }
        boolean increasing = a.get(0)<a.get(1);
        for(int i = 0; i<a.size()-1; i++) {
            if(a.get(i)<a.get(i+1)!=increasing) {
                return false;
            }
            if (Math.abs(a.get(i)-a.get(i+1))<1 || Math.abs(a.get(i)-a.get(i+1))>3) {
                return false;
            }
        }
        return true;
    }
}
