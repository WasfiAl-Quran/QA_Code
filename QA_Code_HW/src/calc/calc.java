package calc;

import java.util.*;

public class calc {

    int result = 0;
    String defaultDelimiters = "[,\n;]";
    String badInputs = "$^+*";
    String newDelimiter = "";
    ArrayList<String> tempDelimiters = new ArrayList<>();
    String newDelimiters = "";
    String currentDelimiter = " ";
    Boolean flag = false;
    String negativeNumbers = "";

    public int add(String stringNumbers) {

        if(stringNumbers.isEmpty())
            return 0;

        if(stringNumbers.charAt(0) == '/' && stringNumbers.charAt(1) == '/'){

            newDelimiter = stringNumbers.split("\n")[0];
            defaultDelimiters = "[;,\n" + ((newDelimiter.substring(2).length() == 1) ? newDelimiter.substring(2) : defaultDelimiters) + "]";

            if(newDelimiter.substring(2).length() > 1){

                for(String delim : newDelimiter.substring(2).split("")){

                    if(currentDelimiter.charAt(currentDelimiter.length() - 1) == delim.charAt(0)){

                        currentDelimiter = (badInputs.contains(delim)) ? (currentDelimiter + "\\" + delim) : (currentDelimiter + delim);
                    }else{

                        tempDelimiters.add(currentDelimiter);
                        currentDelimiter = (badInputs.contains(delim)) ? ("\\" + delim) : (delim);
                    }
                }
                tempDelimiters.add(currentDelimiter);
                tempDelimiters.forEach(delim -> newDelimiters += "|" + delim);
                defaultDelimiters = defaultDelimiters + newDelimiters;
            }

            stringNumbers = stringNumbers.split("\n")[1];
        }

        for(String num : stringNumbers.trim().split(defaultDelimiters)){

            if(Integer.parseInt(num.trim()) < 0){

                flag = true;
                negativeNumbers = negativeNumbers + (num + " ");
            }else if(Integer.parseInt(num) <= 1000) {
                result += Integer.parseInt(num);
            }
        }

        if(flag){

            throw new IllegalArgumentException("Negative numbers are not allowed : " + negativeNumbers);
        }

        return result;
    }
}