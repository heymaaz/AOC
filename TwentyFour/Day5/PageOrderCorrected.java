package TwentyFour.Day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PageOrderCorrected {
    public static void main(String[] args) {
        
        List<String> a = Input.readFile("TwentyFour/Day5/input.txt");
        List<String> rules = new ArrayList<>(a.subList(1,Integer.parseInt(a.get(0))));

        List<String> pages = new ArrayList<>(a.subList(Integer.parseInt(a.get(0)),a.size()));

        HashMap<Integer,List<Integer>> beforeTheseSet = PageOrder.getBeforeTheseMap(rules);
        
        int out = 0;
        for(String page:pages) {
            List<String> temp = new ArrayList<>(Arrays.asList(page.split(",")));
            List<Integer> list = PageOrder.getIncorrectListForCorrection(beforeTheseSet, temp);
            if(list.size()==0)
                continue;
            while(list.size()==2) {
                System.out.println(list);
                System.out.println(temp);
                temp.remove(Integer.toString(list.get(0)));
                temp.add(temp.indexOf(Integer.toString(list.get(1))),Integer.toString(list.get(0)));
                list = PageOrder.getIncorrectListForCorrection(beforeTheseSet, temp);
            }
            
            int midIndex = temp.size()/2;
            out+=Integer.parseInt(temp.get(midIndex));
        }
        System.out.println(out);
    }
}
