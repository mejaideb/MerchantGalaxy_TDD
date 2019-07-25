package com.tavisca.workshops.pratham;

import java.text.ParseException;
import java.util.HashMap;

public class MerchantGalaxy {

    //    public int ConvertToNumeric(String s) {
//        return romanToArebic.get(s);
//
//    }
    private static HashMap<Character, Integer> romanToArabic = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);

    }};
    private HashMap<String, Character> validRoman = new HashMap<>();

    public String query(String statement) throws Exception {
        if (statement.contains("?"))
            return "";
        else if (statement.contains("Credits"))
            return "";
        else {
            handleWordToRomanStatement(statement);
            return "";
        }


    }


    private void handleWordToRomanStatement(String word) throws Exception {
        WordToRomanParser wtrp = new WordToRomanParser();
        String[] wordToRoman = wtrp.wordToRomanParse(word);
        if (isValidLiteral(wordToRoman[1]))
            validRoman.put(wordToRoman[0], wordToRoman[1].charAt(0));
        else
            throw new Exception("Invalid Word");
    }

    private boolean isValidLiteral(String literal) {
        String literals = romanToArabic.keySet().toString();
        return literals.contains(literal);
    }

    public char getRomanLiteralForWord(String information) {
        return validRoman.get(information);
    }
}
