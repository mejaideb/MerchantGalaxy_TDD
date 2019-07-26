import com.tavisca.workshops.pratham.MerchantGalaxy;
import org.junit.Test;

import java.util.HashMap;
import static org.junit.Assert.*;

public class RomanToArabicConvertorTest {


    @Test
    public void convertRomanToArabic()
    {
        RomanToArabicConverter rtac=new RomanToArabicConverter();
        assertEquals(4,rtac.romanToArabicConvertor("IV"));
    }
    //@Test


        
        
}

