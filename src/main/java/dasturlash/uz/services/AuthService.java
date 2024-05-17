package dasturlash.uz.services;

import dasturlash.uz.containers.ComponentContainer;
import dasturlash.uz.controllers.AdminController;
import dasturlash.uz.controllers.UserController;
import dasturlash.uz.dtos.ProfileDTO;
import dasturlash.uz.enums.GeneralStatus;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.repositories.ProfileRepository;
import dasturlash.uz.utills.MD5Util;
import dasturlash.uz.utills.ProfileValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AdminController adminController;
    @Autowired
    private UserController userController;
    @Autowired
    private ComponentContainer componentContainer;
    public Boolean login(String phone, String password) {
        ProfileDTO profileDTO = profileRepository.getProfileByPhone(phone);

        if(profileDTO == null) {
            return false;
        }
        if(!profileDTO.getPassword().equals(MD5Util.encode(password))){
            return false;
        }

        if(!profileDTO.getStatus().equals(GeneralStatus.ACTIVE)) {
            System.out.println("Profile is Blocked");
            return false;
        }

        if(!profileDTO.getVisible()) {
            return false;
        }

        componentContainer.currentProfile = profileDTO;
        if(profileDTO.getRole().equals(ProfileRole.ADMIN)) {
            adminController.start();
        } else if (profileDTO.getRole().equals(ProfileRole.USER)) {
           userController.start();
        }
        return true;
    }

    public Boolean add(ProfileDTO profileDTO) {
        ProfileDTO profile = profileRepository.getProfileByPhone(profileDTO.getPhone());
        if(profile != null) {
            return false;
        }

        if(!ProfileValidationUtil.isValidate(profileDTO)) {
            return false;
        }



        profileDTO.setVisible(true);
        profileDTO.setStatus(GeneralStatus.ACTIVE);
        profileDTO.setRole(ProfileRole.USER);
        profileDTO.setCreatedAt(LocalDateTime.now());

        return profileRepository.create(profileDTO);

    }
}
