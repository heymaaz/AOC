package TwentyFour.Day10;

import java.util.List;

public class Trails {
    public static void main(String[] args) {

        List<List<Integer>> input = Input.readFile("TwentyFour/Day10/input.txt");
        
        System.out.println("Part 1");
        System.out.println(getTrailRatingsSum(input, true));
        System.out.println("Part 2");
        System.out.println(getTrailRatingsSum(input, false));

    }
    static int getTrailRatingsSum(List<List<Integer>> input, boolean part1) {
        int sum = 0;
        for(int i = 0; i<input.size(); i++) {
            for(int j = 0; j<input.get(i).size(); j++) {
                if(input.get(i).get(j)==0) {
                    int temp = recursive(input, i, j, new boolean[input.size()][input.get(0).size()], -1, part1);
                    sum+=temp;
                }
            }
        }
        return sum;
    }
    static int recursive(List<List<Integer>> input, int i, int j, boolean[][] map, int last, boolean part1) {
        if(i<0 || j<0 || i>=input.size() || j>=input.get(i).size())
            return 0;


        if(input.get(i).get(j)!=last+1) {
            return 0;
        }


        if(input.get(i).get(j)==9 && !map[i][j]) {
            map[i][j]=part1; // All paths till here are distinct by nature. In part 1 we only care about reaching the top in any way, in part 2 we care about distinct paths to the top
            return 1;
        }

        last = input.get(i).get(j);
        return recursive(input,i,j+1,map,last, part1)
             + recursive(input,i,j-1,map,last, part1)
             + recursive(input,i+1,j,map,last, part1)
             + recursive(input,i-1,j,map,last, part1);

    }
}
