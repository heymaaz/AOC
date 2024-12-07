package TwentyFour.Day4;

import java.util.List;

public class CountXmas {
    public static void main(String[] args) {
        List<String> a = Input.readFile("TwentyFour/Day4/input.txt");
        int count = 0;
        for(int i = 0; i<a.size(); i++) {
            for(int j = 0; j<a.get(i).length(); j++) {
                if(a.get(i).charAt(j)!='X')
                    continue;
                count+=getAllXmas(a,i,j,false,false,false,false,"X");
            }
        }
        System.out.println(count);
    }
    static int getAllXmas(List<String> a,int i,int j,boolean l,boolean r,boolean u,boolean d,String t) {
        if(!l&&!r&&!u&&!d)
            return 
             getAllXmas(a,i,j,true,false,false,false,t)
            +getAllXmas(a,i,j,false,true,false,false,t)
            +getAllXmas(a,i,j,false,false,true,false,t)
            +getAllXmas(a,i,j,false,false,false,true,t)
            +getAllXmas(a,i,j,true,false,true,false,t)
            +getAllXmas(a,i,j,true,false,false,true,t)
            +getAllXmas(a,i,j,false,true,true,false,t)
            +getAllXmas(a,i,j,false,true,false,true,t);
        if(l) j--;
        if(r) j++;
        if(u) i--;
        if(d) i++;
        if(i<0||j<0||i>=a.size()||j>=a.get(i).length())
            return 0;
        t=t+a.get(i).charAt(j);

        if(t.length()==2&&!t.equals("XM"))
            return 0;

        if(t.length()==3&&!t.equals("XMA"))
            return 0;
        
        if(t.length()>=4&&!t.equals("XMAS"))
            return 0;

        if(t.equals("XMAS"))
            return 1;
        return getAllXmas(a,i,j,l,r,u,d,t);
    }
}
