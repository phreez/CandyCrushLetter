package com.charles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // remark
        System.out.println("Candy Crush Letter Processor");
        System.out.println("Please choose command:");
        System.out.println("1 - Remove Letter,2 - Replace Letter");
        System.out.println("Input your command like this : \n1\naabcccbbad");

        Scanner scanner = new Scanner(System.in);
        String choice = "";
        
        // ensure the input is a valid number
        while (true) {
            try {
                choice = scanner.nextLine();
                if (!choice.equals("1") && !choice.equals("2")) {
                    System.out.println("Invalid command. Please input 1 or 2:");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid command. Please input 1 or 2:");
                scanner.nextLine();
            }
        }

        String input = scanner.nextLine();
        if (choice.equals("1")) {
            ICandyCrushLetter candyCrushLetter = new RemoveLetter();
            candyCrushLetter.process(input);
        } else if (choice.equals("2")) {
            ICandyCrushLetter candyCrushLetter2 = new ReplaceLetter();
            candyCrushLetter2.process(input);
        }
        System.out.println("Done!");
    }
}