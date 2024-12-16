package TwentyFour.Day11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Stones {
    public static void main(String[] args) {

        List<Long> input = Input.readFile("TwentyFour/Day11/input.txt");
        
        System.out.println("After 25 blinks number of stones are: "+blinkAndCache(input,25));
        System.out.println("After 75 blinks number of stones are: "+blinkAndCache(input,75));

        // for(int i = 0; i<10; i++) {
        //     List<Long> tmp = new ArrayList<>();
        //     for(int j = 0; j<input.size(); j++) {
        //         if(input.get(j)==0) {
        //             tmp.add(1l);
        //             continue;
        //         }
        //         String str = Long.toString(input.get(j));
        //         if(str.length()%2==1) {
        //             tmp.add(2024*input.get(j));
        //             continue;
        //         }
        //         int mid = str.length()/2;
        //         tmp.add(Long.parseLong(str.substring(0, mid)));
        //         tmp.add(Long.parseLong(str.substring(mid)));
        //     }
        //     input = new ArrayList<>(tmp);
        //     System.out.println(input);
        // }
        // System.out.println("After 25 blinks number of stones are: "+input.size());

    }

    static long blinkAndCache(List<Long> stones, int maxGen) {
        long total = 0l;
        HashMap<String,Long> cache = new HashMap<>();
        for(long stone:stones) {
            total += expandWithCache(stone, maxGen, cache);
        }
        // System.out.println(cache);
        return total;
    }

    static long expandWithCache(Long stone, int maxGen, HashMap<String,Long> cache) {
        if(maxGen==0)
            return 1;
        String key = Long.toString(stone).concat("-").concat(Integer.toString(maxGen));
        if(cache.containsKey(key)) {
            return cache.get(key);
        }
        List<Long> newStones = expandStone(stone);
        if(maxGen==1) {
            cache.put(key,(long)newStones.size());
            return (long)newStones.size();
        }
        long total = 0l;
        for(long newStone:newStones) {
            total += expandWithCache(newStone, maxGen-1, cache);
        }
        cache.put(key, total);
        
        return total;

    }

    static List<Long> expandStone(Long stone) {
        List<Long> newStones = new ArrayList<>();
        if(stone.equals(0l)) {
            newStones.add(1l);
        }
        else {
            String str = Long.toString(stone);
            if(str.length()%2==1) {
                newStones.add(2024*stone);
            }
            else {
                int mid = str.length()/2;
                newStones.add(Long.parseLong(str.substring(0, mid)));
                newStones.add(Long.parseLong(str.substring(mid)));
            }
        }
        return newStones;
    }
}
