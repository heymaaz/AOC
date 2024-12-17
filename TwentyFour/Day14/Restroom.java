package TwentyFour.Day14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Restroom {
    public static void main(String[] args) {
        List<String> input = Input.readFile("TwentyFour/Day14/input.txt");

        List<Robot> robots = new ArrayList<>();
        for(String line:input) {
            robots.add(parseLine(line));
        }
        
        // int row = 7, col = 11;//for the example 11 tiles wide and 7 tiles tall.
        int row = 103, col = 101;// for the input 101 tiles wide and 103 tiles tall

        int count = 0, time = 1;
        
        // To track maximum robot clustering
        int maxNeighbours = 0;
        int maxNeighbourTime = 0;

        while(count++<10000) {
            int[][] arr = new int[row][col];

            // Track robot positions for this time step
            List<int[]> positions = new ArrayList<>();

            for(Robot robot:robots) {
                int posX = (robot.posX + time*robot.movX)%col;
                int posY = (robot.posY + time*robot.movY)%row;
                robot.posX = posX>=0?posX:col+posX;
                robot.posY = posY>=0?posY:row+posY;

                arr[robot.posY][robot.posX]++;
                positions.add(new int[]{robot.posX, robot.posY});
            }
            
            // Calculate neighbours for this time step
            int neighbours = calculateNeighbours(positions, row, col);
            
            // Track maximum clustering
            if (neighbours > maxNeighbours) {
                maxNeighbours = neighbours;
                maxNeighbourTime = count;
            }

            if(count==100) {
                // After 100 seconds
                long safetyFactor = 
                getQuadrantSum(arr, 0)*
                getQuadrantSum(arr, 1)*
                getQuadrantSum(arr, 2)*
                getQuadrantSum(arr, 3);
                System.out.println("Part 1: " + safetyFactor);
            }


                
        }

        // Part 2: Print time of maximum robot clustering
        System.out.println("Part 2: " + maxNeighbourTime);

    }

    static int calculateNeighbours(List<int[]> positions, int row, int col) {
        int neighbours = 0;
        
        for (int[] pos : positions) { // Outer loop: for each robot
            int x = pos[0];
            int y = pos[1];
            
            // Check 4 adjacent positions (up, down, left, right)
            int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};
            
            for (int[] dir : directions) {  // Check adjacent positions
                int newX = (x + dir[0] + col) % col;
                int newY = (y + dir[1] + row) % row;
                
                for (int[] otherPos : positions) { // Check if another robot is in that position
                    if (otherPos[0] == newX && otherPos[1] == newY) {
                        neighbours++;  // Since we are just checking for clustering we can recount neighbour A as B and neighbour B as A
                    }
                }
            }
        }
        
        return neighbours;
    }

    static String arrayToString(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : arr) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }

    static long getQuadrantSum(int[][] arr, int quad) {
        int lenr=arr.length, lenc = arr[0].length;
        int midr=lenr/2, midc=lenc/2;
        int startr, startc, endr, endc;
        // if(quad == 0) {
            startr=0;
            startc=0;
            endr=midr;
            endc=midc;
        // }
        if(quad==1) {
            startr=0;
            startc=1+midc;
            endr=midr;
            endc=lenc;
        }
        else if(quad==2) {
            startr=1+midr;
            startc=0;
            endr=lenr;
            endc=midc;
        }
        else if(quad==3) {
            startr=1+midr;
            startc=1+midc;
            endr=lenr;
            endc=lenc;
        }
        long count = 0;
        for(int i = startr; i<endr; i++) {
            for(int j = startc; j<endc; j++) {
                count+=arr[i][j];
            }
        }
        return count;
    }

    static Robot parseLine(String line) {
        int idx1 = line.indexOf('=');
        int idx2 = line.indexOf(',');
        int idx3 = line.indexOf(' ');
        int idx4 = line.lastIndexOf('=');
        int idx5 = line.lastIndexOf(',');

        int posX = Integer.parseInt(line.substring(1+idx1, idx2));
        int posY = Integer.parseInt(line.substring(1+idx2, idx3));
        int movX = Integer.parseInt(line.substring(1+idx4, idx5));
        int movY = Integer.parseInt(line.substring(1+idx5));

        return new Robot(posX, posY, movX, movY);
    }

    static class Robot{
        int posX;
        int posY;
        int movX;
        int movY;
        int row = 103, col = 101;

        Robot(int posX, int posY, int movX, int movY) {
            this.posX = posX;
            this.posY = posY;
            this.movX = movX;
            this.movY = movY;
        }

        @Override
        public String toString() {
            // return "P="+this.posX+","+this.posY+" V="+this.movX+","+this.movY;
            String out = "";
            for(int i = 0; i<row; i++) {
                for(int j = 0; j<col; j++) {
                    if(posX==j&&posY==i) {
                        out+="1\t";
                    }
                    else {
                        out+=".\t";
                    }
                }
                out+="\n";
            }
            return out;
        }
    }
}
