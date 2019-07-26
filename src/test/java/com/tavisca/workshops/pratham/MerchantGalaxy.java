package com.tavisca.workshops.pratham;

import java.util.Arrays;
import java.util.HashMap;

public class MerchantGalaxy {

    public static final String RESPONSE_NO_IDEA = "I have no idea what you are talking about";
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
    private HashMap<String, Integer> metalToCreditsMap = new HashMap<>();

    public String query(String statement) throws Exception {
        if (statement.contains("?")) {
            return handleQuestionMarkStatements(statement);
        } else if (statement.contains("Credits")) {
            handleMetalsCreditValuePerItem(statement);
            return "";
        } else {
            handleWordToRomanStatement(statement);
            return "";
        }
    }

    private String handleQuestionMarkStatements(String statement) {
        StatementToCreditTokenParser stctp = new StatementToCreditTokenParser();
        String[] questions = stctp.canParseMuchManyTokens(statement);

        if (statement.contains("much")) {
            return answerQuestionsForMuch(questions);
        }
        else if (statement.contains("many")) {
            return answerQuestionsForMany(questions);
        }else {
            return RESPONSE_NO_IDEA;
        }
    }

    private String answerQuestionsForMany(String[] questions) {
        RomanToArabicConverter rtac = new RomanToArabicConverter();
        String roman = "";
        String response="";

        int i;
        for (i = 0; i < questions.length - 1; i++) {
            roman += validRoman.get(questions[i]);
            response+=questions[i]+ " ";
        }
        response += questions[i] + " ";

        int tokenValue = rtac.romanToArabicConvertor(roman);
        int valueOfMetal = metalToCreditsMap.get(questions[questions.length - 1]);
        int calculateResult = tokenValue * valueOfMetal;

        response+="is "+calculateResult+ " " + "Credits";

        return response;


    }


    private String answerQuestionsForMuch(String[] words) {
        RomanToArabicConverter rtac = new RomanToArabicConverter();
        StatementToCreditTokenParser stctp=new StatementToCreditTokenParser();
        String romanLiterals = "";
        String response = "";
        for (String word : words) {
            romanLiterals += validRoman.get(word);
            response += word + " ";

        }

        int result = rtac.romanToArabicConvertor(romanLiterals);
        response += "is " + String.valueOf(result);

        return response;
    }

    private int handleMetalsCreditValuePerItem(String metal) {
        StatementToCreditTokenParser statementToCreditTokenParser = new StatementToCreditTokenParser();
        String[] storeCreditValue = statementToCreditTokenParser.canParseCreditStatement(metal);
        String metals = storeCreditValue[storeCreditValue.length - 2];
        String creditValue = storeCreditValue[storeCreditValue.length - 1];

        String[] words = Arrays.copyOf(storeCreditValue, storeCreditValue.length - 2);
        int romanToNumeric = convertWordsToRomanAndRomanToArabicValue(words);
        int valueForMetal = Integer.parseInt(creditValue) / romanToNumeric;

        metalToCreditsMap.put(metals, valueForMetal);

        return 0;
    }

    private int convertWordsToRomanAndRomanToArabicValue(String[] words) {
        RomanToArabicConverter rtac = new RomanToArabicConverter();
        String s = "";
        for (String word : words) {
            s += validRoman.get(word);
        }
        return rtac.romanToArabicConvertor(s);

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

    public int getValueForMetal(String metal) {
        return metalToCreditsMap.get(metal);

    }

    public String answerQuestions(String s) {

        return handleQuestionMarkStatements(s);
    }
}
