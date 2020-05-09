package calcWithLog;

import org.junit.Test;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class calcWithLogTest {

    calcWithLog calc =  new calcWithLog(Logger.getLogger("main.java"), new LogHand());

    @Test
    public void add_emptyString_returns0_withLog(){
        assertEquals(0, calc.add(""));
        assertThat(calc.logHand.getLog(), equalTo("Adding procedure occurred, Result: 0"));
    }

    @Test
    public void add_oneNumberString_returns1_withLog(){
        assertEquals(1, calc.add("1"));
        assertThat(calc.logHand.getLog(), equalTo("Adding procedure occurred, Result: 1"));
    }

    @Test
    public void add_twoNumbersString_returns2_withLog(){
        assertEquals(2, calc.add("1,1"));
        assertThat(calc.logHand.getLog(), equalTo("Adding procedure occurred, Result: 2"));
    }

    @Test
    public void add_unknownAmountOfNumbersString_returns5_withLog(){
        assertEquals(5, calc.add("1,1,1,1,1"));
        assertThat(calc.logHand.getLog(), equalTo("Adding procedure occurred, Result: 5"));
    }

    @Test
    public void add_newLineBetweenNumberString_returns2_withLog(){
        assertEquals(2, calc.add("1\n1"));
        assertThat(calc.logHand.getLog(), equalTo("Adding procedure occurred, Result: 2"));
    }

    @Test
    public void add_userDelimitersAllowed_returns2_withLog(){
        assertEquals(2, calc.add("//!\n1!1"));
        assertThat(calc.logHand.getLog(), equalTo("Adding procedure occurred, Result: 2"));
    }

    @Test
    public void add_negativeNumber_throwException_withLog() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> calc.add("1,-1")
        );
        assertTrue(thrown.getMessage().contains("Negative numbers are not allowed : -1"));
        assertTrue(calc.logHand.getLog().contains("Negative numbers are not allowed : -1"));
    }

    @Test
    public void add_numbersBiggerThan1000_ignored_returns1_withLog(){
        assertEquals(1, calc.add("1,1001"));
        assertThat(calc.logHand.getLog(), equalTo("Adding procedure occurred, Result: 1"));
    }

    @Test
    public void add_numbersWithMultipleDelimiters_returns3_withLog(){
        assertEquals(3, calc.add("//!@\n1!1@1"));
        assertThat(calc.logHand.getLog(), equalTo("Adding procedure occurred, Result: 3"));
    }

    @Test
    public void add_numbersWithMultipleDelimitersWithlengthLongerThanOneChar_returns3_withLog(){
        assertEquals(3, calc.add("//!!@@\n1!!1@@1"));
        assertThat(calc.logHand.getLog(), equalTo("Adding procedure occurred, Result: 3"));
    }
}