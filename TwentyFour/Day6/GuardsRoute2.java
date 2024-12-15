package TwentyFour.Day6;

import java.util.ArrayList;
import java.util.List;

public class GuardsRoute2 {
    static List<List<Character>> DeepCopy(List<List<Character>> a) {
        List<List<Character>> copy = new ArrayList<>();
        for(List<Character> b : a) {
            copy.add(new ArrayList<>(b));
        }
        return copy;
    }
    static void countUniqueVisited(List<List<Character>> a, int row, int col, boolean[][] visited) {
        while(true) {
            print(a);
            if(shouldTurn(a, row, col)) {
                a.get(row).set((col),turn(a, row, col));
                continue;
            }
            int tempR=row, tempC=col;
            char c = a.get(row).get(col);
            if(c=='^')
                row--;
            else if(c=='>')
                col++;
            else if(c=='v')
                row++;
            else if(c=='<')
                col--;

            visited[tempR][tempC]=true;

            if(row<0 || col<0 || row>=a.size() || col>=a.get(0).size()) {
                System.out.println("Game Over!");
                break;
            }
            a.get(row).set(col,c);
        }
    }
    public static void main(String[] args) {
        List<List<Character>> a = Input.readFile("TwentyFour/Day6/input.txt");
        int row=0,col=0;
        boolean flag = true;
        for( int i = 0; i<a.size() && flag; i++) {
            for ( int j = 0; j<a.get(0).size() && flag; j++) {
                if(
                    a.get(i).get(j)=='^' ||
                    a.get(i).get(j)=='>' ||
                    a.get(i).get(j)=='<' ||
                    a.get(i).get(j)=='v'
                ) {
                    row=i;
                    col=j;
                    flag = false;
                }
            }
        }

        boolean[][] visited = new boolean[a.size()][a.get(0).size()];
        countUniqueVisited(a,row,col,visited);
        visited[row][col]=false;

        print(a);
        int count = 0;
        for(int i = 0; i<visited.length; i++) {
            for(int j = 0; j<visited[0].length; j++) {
                if (visited[i][j]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
    static void print(List<List<Character>> a) {
        for(List<Character> b : a) {
            for(char c : b) {
                System.out.print(Character.toString(c)+'\t');
            }
            System.out.println();
        }
    }
    static boolean shouldTurn(List<List<Character>> a, int row, int col) {
        if(a.get(row).get(col)=='^')
            row--;
        else if(a.get(row).get(col)=='>')
            col++;
        else if(a.get(row).get(col)=='v')
            row++;
        else if(a.get(row).get(col)=='<')
            col--;
        
        if(row<0 || col<0 || row>=a.size() || col>=a.get(0).size()) {
            return false;
        }

        if(a.get(row).get(col)=='#')
            return true;
        return false;
    }

    static char turn(List<List<Character>> a, int row, int col) {
        if(a.get(row).get(col)=='^') {
            return '>';
        }
        if(a.get(row).get(col)=='>') {
            return 'v';
        }
        if(a.get(row).get(col)=='v') {
            return '<';
        }
        return '^';
    }
}
