package com.charles;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveLetter implements ICandyCrushLetter{
    @Override
    public void process(String input){
        if (input == null || !input.matches("[a-z]*")||input.isBlank()) {
            throw new IllegalArgumentException("only a-z supported");
        }
        // recognize three or more repeated letters regular expression
        Pattern pattern = Pattern.compile("([a-z])\\1{2,}");
        Matcher matchercheck = pattern.matcher(input);
        //if no match found, print the original string
        if (!matchercheck.find()) {
            System.out.println(input);
            return;
        }
        String current = input;
        while (true) {
            Matcher matcher = pattern.matcher(current);
            if (matcher.find()) {
                // replace the first match
                String next = matcher.replaceFirst("");
                System.out.println(next);
                current = next;
            } else {
                break; // no more matches, stop
            }
        }
    }
}
