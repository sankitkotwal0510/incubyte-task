package com.ajinz.main;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int Add(String numbers){
        String delimiter = ",|\n", negative_numbers_str = "", split_numbers[];
        ArrayList<Integer> negative_numbers = new ArrayList<>();

        numbers = numbers.replaceAll(" ", "");  

        if (numbers == "") return 0;  

        int start_index = numbers.indexOf("//");
        int end_index = numbers.indexOf("\\n");

        if (start_index != -1) {
            delimiter = numbers.substring(start_index + 2, end_index);
            numbers = numbers.substring(end_index + 2);

            if (delimiter.endsWith("]") && delimiter.indexOf("[") == 0) {
                Pattern pattern = Pattern.compile("\\[(.*?)\\]");
                Matcher matcher = pattern.matcher(delimiter);

                int groupCount = matcher.groupCount();
                while (matcher.find()) {
                    for (int i = 0; i <= groupCount; i++) {
                        String m = matcher.group(i);
                        numbers = numbers.replaceAll(Pattern.quote(m), ",");
                    }
                }
                delimiter = ",";
            }

            split_numbers = numbers.split(Pattern.quote(delimiter));
        }
        else
            split_numbers = numbers.split(delimiter); 

        // Counting Total of Numbers
        int total = 0;
        for (String n : split_numbers) {
            Integer current = Integer.parseInt(n);
            if (current < 0) {
                negative_numbers.add(current);
                negative_numbers_str += (String.valueOf(current) + " ");
            }
            if(current < 1001) total += current;
        }

        HandleNegativeValues.throwExceptionIfNegativeExists(negative_numbers_str);
        return total;
    }
}