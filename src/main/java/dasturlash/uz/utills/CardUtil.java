package dasturlash.uz.utills;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class CardUtil {
    public static String middle = "****";
    public static String replaceWithStar(String input) {
        String prefix = input.substring(0,4);
        String suffix = input.substring(input.length()-4);

        return prefix + middle + suffix;
    }

    public static Boolean isValidate(String cardNumber) {
        char [] cardNumbers = cardNumber.toCharArray();
        if(cardNumbers.length != 16) {
            return false;
        }

        for (Character character : cardNumbers){
            if(!Character.isDigit(character)) {
                return false;
            }
        }
        return true;
    }

}
