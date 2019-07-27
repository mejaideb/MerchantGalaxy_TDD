package com.tavisca.workshops.pratham;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GalaxyTest {
    MerchantGalaxy merchantGalaxy = new MerchantGalaxy();

    @Test
    public void canParseWordToRomanNumeralStatement() {
        WordToRomanParser wordToRomanParser = new WordToRomanParser();
        assertArrayEquals(new String[]{"glob", "I"}, wordToRomanParser.wordToRomanParse("glob is I"));
        assertArrayEquals(new String[]{"pork", "V"}, wordToRomanParser.wordToRomanParse("pork is V"));
        assertArrayEquals(new String[]{"pish", "X"}, wordToRomanParser.wordToRomanParse("pish is X"));
        assertArrayEquals(new String[]{"tegj", "L"}, wordToRomanParser.wordToRomanParse("tegj is L"));
    }

    @Test
    public void canParseCreditStatement() {
        StatementToCreditTokenParser cpcs = new StatementToCreditTokenParser();
        assertArrayEquals(new String[]{"glob", "glob", "Silver", "34"}, cpcs.canParseCreditStatement("glob glob Silver is 34 Credits"));
        assertArrayEquals(new String[]{"glob", "prok", "Gold", "57800"}, cpcs.canParseCreditStatement("glob prok Gold is 57800 Credits"));
        assertArrayEquals(new String[]{"pish", "pish", "Iron", "3910"}, cpcs.canParseCreditStatement("pish pish Iron is 3910 Credits"));
    }

    @Test
    public void canParseMuchManyTokens() {
        StatementToCreditTokenParser cpcs = new StatementToCreditTokenParser();
        assertArrayEquals(new String[]{"pish", "tegj", "glob", "glob"}, cpcs.canParseMuchManyTokens("how much is pish tegj glob glob ?"));
        assertArrayEquals(new String[]{"glob", "prok", "Silver"}, cpcs.canParseMuchManyTokens("how many Credits is glob prok Silver ?"));
        assertArrayEquals(new String[]{"glob", "prok", "Gold"}, cpcs.canParseMuchManyTokens("how many Credits is glob prok Gold ?"));
        assertArrayEquals(new String[]{"glob", "prok", "Iron"}, cpcs.canParseMuchManyTokens("how many Credits is glob prok Iron ?"));
    }

    @Test
    public void checkValidMap() {
        try {
            merchantGalaxy.query("glob is I");
            merchantGalaxy.query("prok is V");
            merchantGalaxy.query("pish is X");
            merchantGalaxy.query("tegj is L");

            assertEquals('I', merchantGalaxy.getRomanLiteralForWord("glob"));
            assertEquals('V', merchantGalaxy.getRomanLiteralForWord("prok"));
            assertEquals('X', merchantGalaxy.getRomanLiteralForWord("pish"));
            assertEquals('L', merchantGalaxy.getRomanLiteralForWord("tegj"));
        } catch (Exception e) {
            return;
        }
    }

    @Test
    public void checkCreditPerItem() throws Exception {
        checkValidMap();
        merchantGalaxy.query("glob glob Silver is 34 Credits");
        merchantGalaxy.query("glob prok Gold is 57800 Credits");
        merchantGalaxy.query("pish pish Iron is 3910 Credits");
        assertEquals(17, merchantGalaxy.getValueForMetal("Silver"));
        assertEquals(14450, merchantGalaxy.getValueForMetal("Gold"));
        assertEquals(195.5, merchantGalaxy.getValueForMetal("Iron"), 0.5);
    }

    @Test
    public void answerQuestions() throws Exception {
        checkValidMap();
        checkCreditPerItem();
        String response = merchantGalaxy.query("how much is pish tegj glob glob ?");
        String response1 = merchantGalaxy.query("how many Credits is glob prok Silver ?");
        String response2 = merchantGalaxy.query("how many Credits is glob prok Gold ?");
        //String response4 = merchantGalaxy.query("how many Credits is glob prok Iron ?");
        String response3=merchantGalaxy.query("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

        assertEquals("pish tegj glob glob is 42", response);
        assertEquals("glob prok Silver is 68 Credits", response1);
        assertEquals("glob prok Gold is 57800 Credits",response2);
        assertEquals("I have no idea what you are talking about",response3);
        //assertEquals("glob prok Iron is 782 Credits",response4);
    }
}
