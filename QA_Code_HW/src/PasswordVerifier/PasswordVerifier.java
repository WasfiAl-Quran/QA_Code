package PasswordVerifier;

import java.util.*;

public class PasswordVerifier {

    ArrayList<Boolean> checkList = new ArrayList<>(Arrays.asList(false, false, false, false, false));
    
    long count;
    String log = "";

    public void Verify(String password){

        if(!(password.length() <= 8))
            checkList.set(0,true);

        if (!password.isEmpty())
            checkList.set(1,true);

        for(int i=0; i<password.length(); i++) {

            if(Character.isUpperCase(password.charAt(i)))
                checkList.set(2,true);

            if(Character.isLowerCase(password.charAt(i)))
                checkList.set(3,true);

            if(Character.isDigit(password.charAt(i)))
                checkList.set(4,true);
        }

            if (!checkList.get(0)) log += "\npassword should be larger than 8 chars";
            if (!checkList.get(1)) log += "\npassword should not be null";
            if (!checkList.get(2)) log += "\npassword should have one uppercase letter at least";
            if (!checkList.get(3)) log += "\npassword should have one lowercase letter at least";
            if (!checkList.get(4)) log += "\npassword should have one number at least";

            count = checkList.stream().filter(p -> p).count();
            log += (!checkList.get(3)) ? "\nPassword is never OK" : (count >= 3) && checkList.get(3) ? "\nPassword is OK" : "";

            throw new IllegalArgumentException(log);
        }
}
