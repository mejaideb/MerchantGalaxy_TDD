package com.tavisca.workshops.pratham;

public class WordToRomanParser {

    public String[] wordToRomanParse(String word) {
        String wordsToRomanArray[] = word.split(" ");
        return new String[]{wordsToRomanArray[0], wordsToRomanArray[2]};
    }
}
