package dasturlash.uz.utills;

import dasturlash.uz.dtos.ProfileDTO;

public class ProfileValidationUtil {
    public static Boolean isValidate(ProfileDTO profileDTO) {
        if(profileDTO.getName() == null || profileDTO.getName().isBlank() || profileDTO.getName().trim().length() < 3) {
            return false;
        }
        if(profileDTO.getSurname() == null || profileDTO.getSurname().isBlank() || profileDTO.getSurname().trim().length() < 3) {
            return false;
        }
        if(profileDTO.getPassword() == null || profileDTO.getPassword().isBlank() || profileDTO.getPassword().trim().length() < 3) {
            return false;
        }

        if(profileDTO.getPhone() == null ||profileDTO.getPhone().isBlank()
        || profileDTO.getPhone().trim().length() !=13
        || !profileDTO.getPhone().startsWith("+998")
        || !isOnlyNumber(profileDTO.getPhone())) {
            return false;
        }
        return true;
    }
    public static Boolean isOnlyNumber(String phone) {
        char [] phoneOfChars = phone.toCharArray();

        for (int i = 1; i <phoneOfChars.length; i++) {
            if(!Character.isDigit(phoneOfChars[i])) {
                return false;
            }
        }
        return true;
    }
}
