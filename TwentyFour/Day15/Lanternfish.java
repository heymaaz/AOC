package TwentyFour.Day15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lanternfish {
    
    // 4 adjacent positions (up, down, left, right)
    int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    public static void main(String[] args) {
        String input = Input.readFile("TwentyFour/Day15/example.txt");
        String[] a = input.split("\n\n");
        String[] b = a[0].split("\n");
        int[] pos = new int[2];

        List<List<Character>> map = new ArrayList<>();
        for(int i = 0; i<b.length; i++) {
            List<Character> temp = new ArrayList<>();
            for(int j = 0; j<b[i].length(); j++){
                char ch = b[i].charAt(j);
                if(ch=='@') {
                    pos[0]=i;
                    pos[1]=j;
                }
                temp.add(ch);
            }
            map.add(temp);
        }
        
        char[] moves = a[1].replaceAll("\n", "").toCharArray();
        for(char move:moves) {
            moveThis(pos,map);
        }
        long sumOfCoor = 0l;
        for(int i = 0; i<map.size(); i++) {
            for(int j = 0; j<map.get(0).size(); j++) {
                if(map.get(i).get(j)=='O') {
                    sumOfCoor += 10*i +j;
                }
            }
        }
    }
}
