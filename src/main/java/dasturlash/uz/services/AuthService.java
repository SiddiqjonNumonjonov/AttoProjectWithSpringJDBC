package dasturlash.uz.services;

import dasturlash.uz.controllers.AdminController;
import dasturlash.uz.dtos.ProfileDTO;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.repositories.ProfileRepository;
import dasturlash.uz.utills.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AdminController adminController;
    public Boolean login(String phone, String password) {
        ProfileDTO profileDTO = profileRepository.getProfileByPhone(phone);

        if(profileDTO == null) {
            return false;
        }
        if(!profileDTO.getPassword().equals(MD5Util.encode(password))){
            return false;
        }

        if(!profileDTO.getStatus().equals(ProfileStatus.ACTIVE)) {
            System.out.println("Profile is Blocked");
            return false;
        }

        if(profileDTO.getRole().equals(ProfileRole.ADMIN)) {
            adminController.start();
        } else if (profileDTO.getRole().equals(ProfileRole.USER)) {
            System.out.println("user menu");
        }
        return true;
    }
}
