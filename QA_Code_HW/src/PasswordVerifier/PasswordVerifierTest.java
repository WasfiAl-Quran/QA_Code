package PasswordVerifier;

import java.util.*;
import java.util.logging.*;

public class PasswordVerifier {

    ArrayList<Boolean> checkList = new ArrayList<>(Arrays.asList(false, false, false, false, false));
    Logger logger = Logger.getLogger("main.java");
    LogHand logHand = new LogHand();
    long count;
    String log = "";

    public void Verify(String password) {

        Logger.getLogger("main.java").addHandler(logHand);

        if (!(password.length() <= 8))
            checkList.set(0, true);

        if (!password.isEmpty())
            checkList.set(1, true);

        for (int i = 0; i < password.length(); i++) {

            if (Character.isUpperCase(password.charAt(i)))
                checkList.set(2, true);

            if (Character.isLowerCase(password.charAt(i)))
                checkList.set(3, true);

            if (Character.isDigit(password.charAt(i)))
                checkList.set(4, true);

        }

        try {
            if (!checkList.get(0)) throw new IllegalArgumentException("\npassword should be larger than 8 chars");
        } catch (IllegalArgumentException e) {
            log += "\npassword should be larger than 8 chars\n";
        }
        try {
            if (!checkList.get(1)) throw new IllegalArgumentException("\npassword should not be null");
        } catch (IllegalArgumentException e) {
            log += "\npassword should not be null\n";
        }
        try {
            if (!checkList.get(2))
                throw new IllegalArgumentException("\npassword should have one uppercase letter at least");
        } catch (IllegalArgumentException e) {
            log += "\npassword should have one uppercase letter at least\n";
        }
        try {
            if (!checkList.get(3))
                throw new IllegalArgumentException("\npassword should have one lowercase letter at least");
        } catch (IllegalArgumentException e) {
            log += "\npassword should have one lowercase letter at least\n";
        }
        try {
            if (!checkList.get(4)) throw new IllegalArgumentException("\npassword should have one number at least");
        } catch (IllegalArgumentException e) {
            log += "\npassword should have one number at least\n";
        }

        if (!log.isEmpty()) logger.warning(log);
        count = checkList.stream().filter(p -> p).count();
    }
    public String is_PasswordOK (){
        if (count >= 3)
            return "Password is OK";
        return "";
    }

    public String is_PasswordNeverOK (){
        if (!checkList.get(3))
            return "Password is never OK";
        return "";
    }
}
