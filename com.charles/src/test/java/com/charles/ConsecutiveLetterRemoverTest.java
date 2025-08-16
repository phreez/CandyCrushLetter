package com.charles;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ConsecutiveLetterRemoverTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testRemoveRepeatedLetters_NormalCase() {
        ICandyCrushLetter candyCrushLetter = new RemoveLetter();
        candyCrushLetter.process("aaabbdcccce");
        String expectedOutput =
                "bbdcccce" + System.lineSeparator() +
                "bbde" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testRemoveRepeatedLetters_NoRepeatedLetters() {
        ICandyCrushLetter candyCrushLetter = new RemoveLetter();
        candyCrushLetter.process("abcdef");
        String expectedOutput = "abcdef" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testRemoveRepeatedLetters_AllSameLetters() {
        ICandyCrushLetter candyCrushLetter = new RemoveLetter();
        candyCrushLetter.process("aaaaaa");
        String expectedOutput = System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testRemoveRepeatedLetters_MultipleGroups() {
        ICandyCrushLetter candyCrushLetter = new RemoveLetter();
        candyCrushLetter.process("aaaabbbbccccdddd");
        String expectedOutput =
                "bbbbccccdddd" + System.lineSeparator()+
                "ccccdddd"+System.lineSeparator()+
                "dddd"+System.lineSeparator()+
                "" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testRemoveRepeatedLetters_EmptyString() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ICandyCrushLetter candyCrushLetter = new RemoveLetter();
            candyCrushLetter.process("   ");
        });
        assertEquals("only a-z supported", exception.getMessage());
    }

    @Test
    public void testRemoveRepeatedLetters_SingleCharacter() {
        ICandyCrushLetter candyCrushLetter = new RemoveLetter();
        candyCrushLetter.process("a");
        String expectedOutput ="a"+System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testRemoveRepeatedLetters_TwoSameCharacters() {
        ICandyCrushLetter candyCrushLetter = new RemoveLetter();
        candyCrushLetter.process("aa");
        String expectedOutput ="aa"+System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testRemoveRepeatedLetters_ThreeSameCharacters() {
        ICandyCrushLetter candyCrushLetter = new RemoveLetter();
        candyCrushLetter.process("aaa");
        String expectedOutput = System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testRemoveRepeatedLetters_InvalidInput_Uppercase() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ICandyCrushLetter candyCrushLetter = new RemoveLetter();
            candyCrushLetter.process("aaA");
        });
        assertEquals("only a-z supported", exception.getMessage());
    }

    @Test
    public void testRemoveRepeatedLetters_InvalidInput_Numbers() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ICandyCrushLetter candyCrushLetter = new RemoveLetter();
            candyCrushLetter.process("aa1");
        });
        assertEquals("only a-z supported", exception.getMessage());
    }

    @Test
    public void testRemoveRepeatedLetters_InvalidInput_SpecialCharacters() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ICandyCrushLetter candyCrushLetter = new RemoveLetter();
            candyCrushLetter.process("aa!");
        });
        assertEquals("only a-z supported", exception.getMessage());
    }
}