package com.charles;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceLetter implements ICandyCrushLetter{
    @Override
    public void process(String input) {
        if (input == null || !input.matches("[a-z]*")||input.isBlank()) {
            throw new IllegalArgumentException("only a-z supported");
        }
        // recognize three or more repeated letters using regular expression
        Pattern pattern = Pattern.compile("([a-z])\\1{2,}");
        Matcher matchercheck = pattern.matcher(input);
        //if no match found, print the original string
        if (!matchercheck.find()) {
            System.out.println(input);
            return;
        }
        String current = input;
        String prompt="";
        while (true) {
            Matcher matcher = pattern.matcher(current);
            if (matcher.find()) {
                if (!prompt.equals("")) {
                    System.out.println(prompt);
                }
                //reset prompt to empty
                prompt="";
                // get first matched letter and string
                String matchedChar = matcher.group(1);
                String matchedString = matcher.group();
                // calculate previous letter
                char previousChar = (char) (matchedChar.charAt(0) - 1);
                // if is a, replace with ""
                String replacement = (matchedChar.charAt(0) == 'a') ? "" : String.valueOf(previousChar);
                // replace the first match
                String next = matcher.replaceFirst(replacement);
                System.out.print(next);
                prompt=","+matchedString+" is replaced by "+replacement;
                current = next;
            } else {
                break; // no more matches, stop
            }
        }
    }
}
