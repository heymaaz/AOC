package TwentyFour.Day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BridgeRepair {
    
    public static void main(String[] args) {
        List<String> input = Input.readFile("TwentyFour/Day7/input.txt");
        List<List<Long>> parsedInput = new ArrayList<>();
        List<Long> answers = new ArrayList<>();
        parse(input, parsedInput, answers);

        Long out1 = 0l, out2=0l;
        for(int i = 0; i<answers.size(); i++) {
            Long answer = answers.get(i);
            List<Long> numbers = parsedInput.get(i);
            if(recursive(answer,numbers,numbers.get(0), 1, false)) {
                out1+=answer;
            }
            if(recursive(answer,numbers,numbers.get(0), 1, true)) {
                out2+=answer;
            }

        }
        System.out.println("Without Concatenation operator ( || )");
        System.out.println(out1);
        System.out.println("With Concatenation operator ( || )");
        System.out.println(out2);

    }

    static boolean recursive(Long answer, List<Long> numbers, Long num, int index, boolean use_concat) {
        if(index >= numbers.size()) 
            return num.equals(answer);
        
        if(num > answer)
            return false;
        
        Long temp = Long.parseLong(Long.toString(num).concat(Long.toString(numbers.get(index))));
        
        return recursive(answer,numbers,num*numbers.get(index),index+1, use_concat) 
        || recursive(answer,numbers,num+numbers.get(index),index+1, use_concat) 
        || use_concat && recursive(answer, numbers, temp, index+1, use_concat) ;
    }
    
    static void parse(List<String> input, List<List<Long>> parsedInput, List<Long> answers) {
        for( String line: input ) {
            List<Long> temp = new ArrayList<>();
            int index = line.indexOf(":");
            int index2 = line.indexOf(" ");
            answers.add(Long.parseLong(line.substring(0,index)));
            
            List<String> xyz = new ArrayList<>(Arrays.asList ( line.substring(1 + index2).split(" ") ));
            
            for ( String t : xyz ) {
                temp.add(Long.parseLong(t));
            }
            parsedInput.add(temp);
        }
    }
}
