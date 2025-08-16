package com.charles;

public class Main {
    public static void main(String[] args) {
       ICandyCrushLetter candyCrushLetter = new RemoveLetter();
       candyCrushLetter.process("aaabbdcccce");
       // ConsecutiveLetterRemover.removeConsecutiveLetters("aa");
//       ICandyCrushLetter candyCrushLetter2 = new ReplaceLetter();
//       candyCrushLetter2.process("abcccbad");
    }
}