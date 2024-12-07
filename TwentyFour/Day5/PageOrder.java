package TwentyFour.Day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PageOrder {
    public static void main(String[] args) {
        
        List<String> a = Input.readFile("TwentyFour/Day5/input.txt");
        List<String> rules = new ArrayList<>(a.subList(1,Integer.parseInt(a.get(0))));

        List<String> pages = new ArrayList<>(a.subList(Integer.parseInt(a.get(0)),a.size()));

        HashMap<Integer,List<Integer>> beforeTheseSet = getBeforeTheseMap(rules);
        
        int out = 0;
        for(String page:pages) {
            List<String> temp = new ArrayList<>(Arrays.asList(page.split(",")));
            List<Integer> list = getIncorrectListForCorrection(beforeTheseSet, temp);
            if(list.size()!=0)
                continue;
            
            int midIndex = temp.size()/2;
            out+=Integer.parseInt(temp.get(midIndex));
        }
        System.out.println(out);
    }

    static HashMap<Integer,List<Integer>> getBeforeTheseMap(List<String> rules) {
        HashMap<Integer,List<Integer>> beforeTheseSet = new HashMap<>();
        
        for(String rule:rules) {
            int index = rule.indexOf("|");
            int prev = Integer.parseInt(rule.substring(0, index));
            int next = Integer.parseInt(rule.substring(index+1));
            List<Integer> temp = beforeTheseSet.getOrDefault(prev, new ArrayList<>());
            temp.add(next);
            beforeTheseSet.put(prev,temp);
        }
        return beforeTheseSet;
    }

    static List<Integer> getIncorrectListForCorrection(HashMap<Integer,List<Integer>> beforeTheseSet, List<String> temp) {
        HashSet<Integer> set = new HashSet<>();
        for(String strNum:temp) {
            int num = Integer.parseInt(strNum);
            for(int j : beforeTheseSet.getOrDefault(num,new ArrayList<>())) {
                if(set.contains(j)) {
                    return Arrays.asList(num,j);
                }
            }
            set.add(num);
        }
        return new ArrayList<>();
    }
}
