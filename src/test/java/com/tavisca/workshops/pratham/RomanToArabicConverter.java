import java.util.HashMap;

public class RomanToArabicConverter
{
    private HashMap<Character, Integer> romanToArabic = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);

    }};

    public int romanToArabicConvertor(String roman) {
        int out = 0;
        char lastchar = 'M';
        char[] a = roman.toCharArray();

        for (char character : a)
        {
            out += romanToArabic.get(character);
            System.out.println(out);
            if (romanToArabic.get(lastchar) < romanToArabic.get(character))
            {
                out -= romanToArabic.get(lastchar) * 2;
            }
            lastchar = character;


        }
        return out;
    }

}
