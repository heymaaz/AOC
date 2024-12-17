package TwentyFour.Day13;

import java.util.ArrayList;
import java.util.List;

public class ClawMachine {
    public static void main(String[] args) {
        String input = Input.readFile("TwentyFour/Day13/input.txt");

        List<Machine> clawMachines = new ArrayList<>();

        String[] a = input.split("\n\n");
        for( String b:a) {
            String lines[] = b.split("\n");
            Machine t = new Machine(parseXY(lines[0]),parseXY(lines[1]),parseXY(lines[2]));
            clawMachines.add(t);
        }

        System.out.println(clawMachines.get(0));

        int min_tokens = 0;

        for(Machine clawMachine:clawMachines) {
            min_tokens+=getMinTokens(clawMachine);
        }
        System.out.println("Minimum Tokens= "+min_tokens);

    }

    static int getMinTokens(Machine clawMachine) {
        int min_tokens = Integer.MAX_VALUE;
        int[] A = clawMachine.A;
        int[] B = clawMachine.B;
        int[] Prize = clawMachine.Prize;
        for(int i = 0; i<=100; i++) {
            for(int j = 0; j<=100; j++) {
                if(i*A[0]+j*B[0]==Prize[0] && i*A[1]+j*B[1]==Prize[1]) {
                    int tokens_used = 3*i +j;
                    min_tokens = Math.min(min_tokens, tokens_used);
                }
            }
        }

        return min_tokens>500?0:min_tokens;
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

    static class Machine{
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
