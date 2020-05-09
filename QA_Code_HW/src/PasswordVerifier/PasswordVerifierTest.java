package PasswordVerifier;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

public class PasswordVerifierTest {

    PasswordVerifier PasswordVerifier = new PasswordVerifier();

    @Test
    public void passwordShouldBeLargerThan8Chars(){
        PasswordVerifier.Verify("aAAA11a");
        assertThat(PasswordVerifier.logHand.getLog(), containsString("password should be larger than 8 chars"));
    }

    @Test
    public void passwordShouldNotBeNull(){
        PasswordVerifier.Verify("");
        assertThat(PasswordVerifier.logHand.getLog(), containsString("password should not be null"));
    }

    @Test
    public void passwordShouldHaveOneUppercaseLetterAtLeast(){
        PasswordVerifier.Verify("aaaaaa11a");
        assertThat(PasswordVerifier.logHand.getLog(), containsString("password should have one uppercase letter at least"));
    }

    @Test
    public void passwordShouldHaveOneLowercaseLetterAtLeast(){
        PasswordVerifier.Verify("AAAAAAAA");
        assertThat(PasswordVerifier.logHand.getLog(), containsString("password should have one lowercase letter at least"));
    }

    @Test
    public void passwordShouldHaveOneNumberAtLeast(){
        PasswordVerifier.Verify("aAAAAAAAa");
        assertThat(PasswordVerifier.logHand.getLog(), containsString("password should have one number at least"));
    }

    @Test
    public void ifThreeConditions_areTrue_return_PasswordIsOK(){
        PasswordVerifier.Verify("aaaaa11a");
        assertTrue(PasswordVerifier.is_PasswordOK ().contains("Password is OK"));
    }

    @Test
    public void ifNotOneLowercaseLetterAtLeast_return_PasswordIsNeverOK(){
        PasswordVerifier.Verify("AAAAA11A");
        assertTrue(PasswordVerifier.is_PasswordNeverOK ().contains("Password is never OK"));
    }
}