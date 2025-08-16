package com.charles;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ConsecutiveLetterReplaceTest {
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
    public void testReplaceRepeatedLetters_NormalCase() {
        ICandyCrushLetter candyCrushLetter = new ReplaceLetter();
        candyCrushLetter.process("abcccbad");
        String expectedOutput ="abbbad,ccc is replaced by b"+ System.lineSeparator() +
                "aaad,bbb is replaced by a" + System.lineSeparator() +
                "d";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testReplaceRepeatedLetters_NoRepeatedLetters() {
        ICandyCrushLetter candyCrushLetter = new ReplaceLetter();
        candyCrushLetter.process("abcdef");
        String expectedOutput = "abcdef" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testReplaceRepeatedLetters_AllSameLetters() {
        ICandyCrushLetter candyCrushLetter = new ReplaceLetter();
        candyCrushLetter.process("aaaaaa");
        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testReplaceRepeatedLetters_MultipleGroups() {
        ICandyCrushLetter candyCrushLetter = new ReplaceLetter();
        candyCrushLetter.process("bbbbccccdddd");
        String expectedOutput =
                "accccdddd,bbbb is replaced by a" + System.lineSeparator()+
                        "abdddd,cccc is replaced by b"+System.lineSeparator()+
                        "abc";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testReplaceRepeatedLetters_EmptyString() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ICandyCrushLetter candyCrushLetter = new ReplaceLetter();
            candyCrushLetter.process("   ");
        });
        assertEquals("only a-z supported", exception.getMessage());
    }

    @Test
    public void testReplaceRepeatedLetters_SingleCharacter() {
        ICandyCrushLetter candyCrushLetter = new ReplaceLetter();
        candyCrushLetter.process("a");
        String expectedOutput ="a"+System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testReplacRepeatedLetters_TwoSameCharacters() {
        ICandyCrushLetter candyCrushLetter = new ReplaceLetter();
        candyCrushLetter.process("aa");
        String expectedOutput ="aa"+System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testReplacRepeatedLetters_ThreeSameCharacters() {
        ICandyCrushLetter candyCrushLetter = new ReplaceLetter();
        candyCrushLetter.process("bbb");
        String expectedOutput = "a";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testReplacRepeatedLetters_InvalidInput_Uppercase() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ICandyCrushLetter candyCrushLetter = new ReplaceLetter();
            candyCrushLetter.process("aaA");
        });
        assertEquals("only a-z supported", exception.getMessage());
    }

    @Test
    public void testReplacRepeatedLetters_InvalidInput_Numbers() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ICandyCrushLetter candyCrushLetter = new ReplaceLetter();
            candyCrushLetter.process("aa1");
        });
        assertEquals("only a-z supported", exception.getMessage());
    }

    @Test
    public void testRemoveRepeatedLetters_InvalidInput_SpecialCharacters() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ICandyCrushLetter candyCrushLetter = new ReplaceLetter();
            candyCrushLetter.process("aa!");
        });
        assertEquals("only a-z supported", exception.getMessage());
    }
}
