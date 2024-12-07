package TwentyFour.Day3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MulSum {
    public static void main(String[] args) {
        String input = Input.readFile("TwentyFour/Day3/input.txt");
        List<String> results = getMatches(input);
        long sum = 0l;
        boolean flag = true;
        for(String result:results) {
            System.out.println(result);
            if (result.equals("do()")) {
                flag=true;
                continue;
            }
            if (result.equals("don't()")) {
                flag=false;
                continue;
            }
            if(flag) {
                int a = Integer.parseInt(result.substring(1+result.indexOf("("),result.indexOf(",")));
                int b = Integer.parseInt(result.substring(1+result.indexOf(","),result.indexOf(")")));
                sum+=(long)a*b;
            }
        }
        System.out.println(sum);
    }

    static List<String> getMatches(String input) {
        List<String> results = new ArrayList<>();

        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            results.add(matcher.group());
        }
        return results;
    }
}
