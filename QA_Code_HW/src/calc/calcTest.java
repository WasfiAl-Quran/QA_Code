package calc;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class calcTest {

    calc calc =  new calc();

    @Test
    public void add_emptyString_returns0(){
        assertEquals(0, calc.add(""));
    }

    @Test
    public void add_oneNumberString_returns1(){
        assertEquals(1, calc.add("1"));
    }

    @Test
    public void add_twoNumbersString_returns2(){
        assertEquals(2, calc.add("1,1"));
    }

    @Test
    public void add_unknownAmountOfNumbersString_returns5(){
        assertEquals(5, calc.add("1,1,1,1,1"));
    }

    @Test
    public void add_newLineBetweenNumberString_returns2(){
        assertEquals(2, calc.add("1\n1"));
    }

    @Test
    public void add_userDelimitersAllowed_returns2(){
        assertEquals(2, calc.add("//!\n1!1"));
    }

    @Test
    public void add_negativeNumber_throwException() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> calc.add("1,-1")
        );
        assertTrue(thrown.getMessage().contains("Negative numbers are not allowed : -1"));
    }

    @Test
    public void add_numbersBiggerThan1000_ignored_returns1(){
        assertEquals(1, calc.add("1,1001"));
    }

    @Test
    public void add_numbersWithMultipleDelimiters_returns3(){
        assertEquals(3, calc.add("//!@\n1!1@1"));
    }

    @Test
    public void add_numbersWithMultipleDelimitersWithlengthLongerThanOneChar_returns3(){
        assertEquals(3, calc.add("//!!@@\n1!!1@@1"));
    }
}
