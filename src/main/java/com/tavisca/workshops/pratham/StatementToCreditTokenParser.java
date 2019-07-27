package com.tavisca.workshops.pratham;

public class StatementToCreditTokenParser {

    public String[] canParseCreditStatement(String inputs) {
        String words[] = inputs.split(" ");

        int indexOfIs = -1;
        int indexOfNumber = -1;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("is"))
                indexOfIs = i;
            else if (words[i].equals("Credits"))
                indexOfNumber = i - 1;
        }

        //Inserting the tokens into new array.
        String[] tokens = new String[indexOfIs + 1];

        for (int i = 0; i < tokens.length - 1; i++)
            tokens[i] = words[i];
        tokens[tokens.length - 1] = words[indexOfNumber];

        return tokens;
    }


    public String[] canParseMuchManyTokens(String s) {
        String[] tokens = s.split(" ");
        int indexOfIs = -1;
        int indexOfQuestionMark = -1;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("is"))
                indexOfIs = i;
            else if (tokens[i].equals("?"))
                indexOfQuestionMark = i;
        }
        String[] wordsToken = new String[indexOfQuestionMark - indexOfIs - 1];
        for (int j = 0; j < wordsToken.length; j++)
            wordsToken[j] = tokens[j + indexOfIs + 1];
        return wordsToken;
    }
}
