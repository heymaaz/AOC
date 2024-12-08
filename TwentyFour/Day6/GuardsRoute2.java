package TwentyFour.Day6;

import java.util.List;

public class GuardsRoute2 {
    public static void main(String[] args) {
        List<List<Character>> a = Input.readFile("TwentyFour/Day6/example.txt");
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
        
        while(true) {
            GuardsRoute.print(a);
            if(GuardsRoute.shouldTurn(a, row, col)) {
                a.get(row).set((col),GuardsRoute.turn(a, row, col));
                continue;
            }
            int tempR=row, tempC=col;
            char c = a.get(row).get(col);
            char prev;
            if(c=='^') {
                row--;
                prev = '|';
            }
            else if(c=='>'){
                col++;
                prev = '-';
            }
            else if(c=='v'){
                row++;
                prev = '-';
            }
            else if(c=='<'){
                col--;
                prev = '-';
            }
            // a.get(tempR).set(tempC,'X');
            visited[tempR][tempC]=true;

            if(row<0 || col<0 || row>=a.size() || col>=a.get(0).size()) {
                System.out.println("Game Over!");
                break;
            }
            a.get(row).set(col,c);

        }

        GuardsRoute.print(a);
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
}
