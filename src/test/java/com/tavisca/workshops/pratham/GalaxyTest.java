package com.tavisca.workshops.pratham;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class GalaxyTest {


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
    }

    @Test
    public void canParseMuchManyTokens() {
        StatementToCreditTokenParser cpcs = new StatementToCreditTokenParser();
        assertArrayEquals(new String[]{"pish", "tegj", "glob", "glob"}, cpcs.canParseMuchManyTokens("how much is pish tegj glob glob ?"));
        assertArrayEquals(new String[]{"glob", "prok", "Silver"}, cpcs.canParseMuchManyTokens("how many Credits is glob prok Silver ?"));
        assertArrayEquals(new String[]{"glob", "prok", "Gold"}, cpcs.canParseMuchManyTokens("how many Credits is glob prok Gold ?"));
        assertArrayEquals(new String[]{"glob", "prok", "Iron"}, cpcs.canParseMuchManyTokens("how many Credits is glob prok Iron ?"));
        //assertArrayEquals(new String[]{"pish","tegj","glob","glob"},cpcs.canParseMuchManyTokens("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"));
    }

    @Test
    public void checkValidMap() {
        try {
            MerchantGalaxy merchantGalaxy = new MerchantGalaxy();
            merchantGalaxy.query("glob is I");

            assertEquals('I', merchantGalaxy.getRomanLiteralForWord("glob"));
        } catch (Exception e) {
            return;
        }
    }
}
