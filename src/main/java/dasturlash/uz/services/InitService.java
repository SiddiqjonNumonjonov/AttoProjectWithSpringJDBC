package dasturlash.uz.services;

import dasturlash.uz.dtos.ProfileDTO;
import dasturlash.uz.enums.GeneralStatus;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.repositories.ProfileRepository;
import dasturlash.uz.utills.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InitService {
    @Autowired
    private ProfileRepository profileRepository;
    private String phone = "+998887122829";
    public void initAdmin() {
        ProfileDTO profileDTO = profileRepository.getProfileByPhone(phone);
        if(profileDTO == null) {
            ProfileDTO profile = new ProfileDTO();
            profile.setName("Siddiqjon");
            profile.setSurname("Numonjonov");
            profile.setPassword(MD5Util.encode("12345"));
            profile.setCreatedAt(LocalDateTime.now());
            profile.setRole(ProfileRole.ADMIN);
            profile.setStatus(GeneralStatus.ACTIVE);
            profile.setVisible(true);
            profile.setPhone("+998887122829");

            profileRepository.create(profile);
        }
    }
}
