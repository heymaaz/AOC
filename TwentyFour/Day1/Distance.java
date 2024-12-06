package TwentyFour.Day1;

import java.util.List;

class Distance {
    public static void main(String[] args) {
        List<List<Integer>> input = Input.readFile("TwentyFour/Day1/input.txt");
        int distance = 0;
        for(int i = 0; i<input.get(0).size(); i++) {
            int temp = input.get(0).get(i)-input.get(1).get(i);
            distance += Math.abs(temp);
            // System.out.println(input.get(0).get(i)+"\t"+input.get(1).get(i)+"\t"+temp);
        }
        System.out.println(distance);
    }
}