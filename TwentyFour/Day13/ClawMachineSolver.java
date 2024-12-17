package TwentyFour.Day13;

import java.util.ArrayList;
import java.util.List;

// Solved using linear algebra (system of 2 equations and 2 variables)
// adapted from https://github.com/BigBear0812/AdventOfCode/blob/master/2024/Day13.js

public class ClawMachineSolver {
    
    public static void main(String[] args) {
        String input = Input.readFile("TwentyFour/Day13/input.txt");

        List<Machine> clawMachines = new ArrayList<>();

        String[] a = input.split("\n\n");
        for(String b : a) {
            String[] lines = b.split("\n");
            Machine t = new Machine(parseXY(lines[0]), parseXY(lines[1]), parseXY(lines[2]));
            clawMachines.add(t);
        }

        // Part 1: original coordinates
        long part1Tokens = getTotalTokens(clawMachines, false);
        System.out.println("Part1 Minimum Tokens = " + part1Tokens);

        // Part 2: adjusted coordinates (adding 10,000,000,000,000)
        long part2Tokens = getTotalTokens(clawMachines, true);
        System.out.println("Part2 Minimum Tokens = " + part2Tokens);
    }

    /**
     * Linear Algebra Trick in the Code:
     * 
     * The code solves the system of equations for each machine:
     * 
     *   A_x * A_clicks + B_x * B_clicks = Prize_x
     *   A_y * A_clicks + B_y * B_clicks = Prize_y
     * 
     * Instead of brute forcing combinations, it uses algebraic manipulation:
     * 1. Multiply the first equation by B_y:
     *    (A_x * A_clicks + B_x * B_clicks) * B_y = Prize_x * B_y
     * 
     * 2. Multiply the second equation by B_x and negate it:
     *    - (A_y * A_clicks + B_y * B_clicks) * B_x = - Prize_y * B_x
     * 
     * When these modified equations are added, the terms involving B_clicks cancel out:
     * 
     *   (A_x * B_y - A_y * B_x) * A_clicks = (Prize_x * B_y - Prize_y * B_x)
     * 
     * From this, the code isolates A_clicks:
     * 
     *   A_clicks = (Prize_x * B_y - Prize_y * B_x) / (A_x * B_y - A_y * B_x)
     * 
     * If A_clicks is a whole integer (no remainder), then the code uses it to solve for B_clicks:
     * 
     *   B_clicks = (Prize_x - A_x * A_clicks) / B_x
     * 
     * Both A_clicks and B_clicks must be integers for the solution to be valid. 
     * If they are, the machine is considered solvable, and their values are added 
     * to the running totals of A and B presses across all machines.
     * 
     * By summing all valid solutions, the total cost can be computed as:
     * 
     *   total cost = (total A_clicks * 3) + (total B_clicks)
     * 
     * For part 2, the code simply adds 10,000,000,000,000 to the prize coordinates 
     * before performing the same calculations, adjusting for the puzzle's offset.
     * @param machines list of machines
     * @param part2 if true, add 10,000,000,00000 to prize coordinates
     * @return total cost to solve all machines (that have integral solutions)
     */
    static long getTotalTokens(List<Machine> machines, boolean part2) {
        long totalA = 0;
        long totalB = 0;

        for (Machine machine : machines) {
            // Apply offset if part2
            long prizeX = part2 ? machine.Prize[0] + 10000000000000L : machine.Prize[0];
            long prizeY = part2 ? machine.Prize[1] + 10000000000000L : machine.Prize[1];

            long aClicksXMultiplier = machine.A[0] * (long)machine.B[1];
            long aClicksYMultiplier = machine.A[1] * (long)machine.B[0] * -1;
            long prizeXMultiplied = prizeX * machine.B[1];
            long prizeYMultiplied = prizeY * machine.B[0] * -1;

            long aClicksMultiplierCombined = aClicksXMultiplier + aClicksYMultiplier;
            long prizeMultipliedCombined = prizeXMultiplied + prizeYMultiplied;

            // Check for division by zero
            if (aClicksMultiplierCombined == 0) {
                // No unique solution in this approach
                continue;
            }

            long remainderA = prizeMultipliedCombined % aClicksMultiplierCombined;
            if (remainderA == 0) {
                long aClicks = prizeMultipliedCombined / aClicksMultiplierCombined;

                // Solve for B
                // BClicks = (PrizeX - A.x*AClicks)/B.x
                long numeratorB = (prizeX - machine.A[0] * aClicks);
                if (machine.B[0] == 0) {
                    // If B.x = 0, can't solve this way.
                    continue;
                }

                long remainderB = numeratorB % machine.B[0];
                if (remainderB == 0) {
                    long bClicks = numeratorB / machine.B[0];

                    // Both A and B must be +ve integers
                    if (aClicks<0 || bClicks<0) continue;

                    totalA += aClicks;
                    totalB += bClicks;
                }
            }
        }

        return totalA * 3 + totalB;
    }

    static int[] parseXY(String str) {
        str=str.trim();
        int idx1 = str.indexOf('X');
        int idx2 = str.indexOf(',');
        int idx3 = str.indexOf('Y');
        int x = Integer.parseInt(str.substring(idx1+2, idx2));
        int y = Integer.parseInt(str.substring(idx3+2));
        return new int[] {x,y};
    }

    static class Machine {
        int[] A;
        int[] B;
        int[] Prize;
        int ACost=3;
        int BCost=1;
        int AMax=100;
        int BMax=100;

        Machine(int[] A,int[] B, int[] Prize) {
            this.A=A;
            this.B=B;
            this.Prize=Prize;
        }

        @Override
        public String toString() {
            return "A- x:"+A[0]+", y:"+A[1]+" Cost:"+ACost+" Max:"+AMax+"\nB- x:"+B[0]+", y:"+B[1]+" Cost:"+BCost+" Max:"+BMax+"\nPrize- x:"+Prize[0]+", y:"+Prize[1];
        }
    }
}
