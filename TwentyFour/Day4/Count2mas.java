package TwentyFour.Day4;

import java.util.ArrayList;
import java.util.List;

public class Count2mas {
    public static void main(String[] args) {
        List<String> a = Input.readFile("TwentyFour/Day4/input.txt");
        int count = 0;
        for(int i = 0; i<a.size(); i++) {
            for(int j = 0; j<a.get(i).length(); j++) {
                if(a.get(i).charAt(j)!='A')
                    continue;
                if(is2MAS(a,i,j))
                    count++;
            }
        }
        System.out.println(count);
    }
    static boolean is2MAS(List<String> a,int i,int j) {
        if(i-1<0||j-1<0||i+1>=a.size()||j+1>=a.get(i).length())
            return false;
        int countM=0,countS=0;
        List<Character> t = new ArrayList<>();
        
        t.add(a.get(i-1).charAt(j-1));
        t.add(a.get(i+1).charAt(j-1));
        t.add(a.get(i-1).charAt(j+1));
        t.add(a.get(i+1).charAt(j+1));
        if(t.get(0)==t.get(3))
            return false;
        
        for(char c : t) {
            if(c=='M')
                countM++;
            if(c=='S')
                countS++;
        }

        if(countM==2&&countS==2)
            return true;
        return false;
        
    }
}
