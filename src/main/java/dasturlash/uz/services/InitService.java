package dasturlash.uz.services;

import dasturlash.uz.dtos.ProfileDTO;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.repositories.ProfileRepository;
import dasturlash.uz.utills.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InitService {
    @Autowired
    private ProfileRepository profileRepository;
    private String admin = "admin";
    public void initAdmin() {
//        ProfileDTO profileDTO = profileRepository.getProfileById();

        ProfileDTO profile = new ProfileDTO();
        profile.setName("Siddiqjon");
        profile.setSurname("Numonjonov");
        profile.setPassword("12345");
        profile.setPassword(MD5Util.encode("12345"));
        profile.setCreatedAt(LocalDateTime.now());
        profile.setRole(ProfileRole.ADMIN);
        profile.setStatus(ProfileStatus.ACTIVE);
        profile.setVisible(true);
        profile.setPhone("+998887122829");

        profileRepository.create(profile);
    }
}
