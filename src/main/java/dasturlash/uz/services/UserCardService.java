package dasturlash.uz.services;

import dasturlash.uz.containers.ComponentContainer;
import dasturlash.uz.dtos.CardDTO;
import dasturlash.uz.dtos.ProfileCard;
import dasturlash.uz.dtos.ProfileDTO;
import dasturlash.uz.enums.GeneralStatus;
import dasturlash.uz.repositories.CardRepository;
import dasturlash.uz.repositories.ProfileRepository;
import dasturlash.uz.repositories.UserCardRepository;
import dasturlash.uz.utills.CardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserCardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private ComponentContainer componentContainer;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserCardRepository userCardRepository;

    public Boolean addToCard(String cardNumber) {
        if (!CardUtil.isValidate(cardNumber)) {
            return false;
        }

        CardDTO cardDTO = cardRepository.getCardByCardNumber(cardNumber);
        if (cardDTO == null) {
            return false;
        }

        ProfileDTO profileDTO = profileRepository.getProfileByPhone(componentContainer.currentProfile.getPhone());
        if (profileDTO == null) {
            return false;
        }

        ProfileCard profileCard = new ProfileCard();
        profileCard.setCardId(cardDTO.getId());
        profileCard.setProfileId(profileDTO.getId());
        profileCard.setVisible(true);
        profileCard.setCreatedAt(LocalDateTime.now());

        return userCardRepository.connectCardToProfile(profileCard);
    }

    public List<ProfileCard> cardLists() {
        return userCardRepository.cardLists(componentContainer.currentProfile.getId());
    }

    public Boolean changeCardStatus(String cardNumber) {
        if (!CardUtil.isValidate(cardNumber)) {
            return false;
        }

        CardDTO cardDTO = cardRepository.getCardByCardNumber(cardNumber);
        if (cardDTO == null) {
            return false;
        }
        var lists = cardLists();
        for (ProfileCard card : lists) {
            if (card.getCardDTO().getCardNumber().equals(cardDTO.getCardNumber()) && cardDTO.getStatus().equals(GeneralStatus.ACTIVE)) {
                return userCardRepository.changeStatus(cardDTO.getCardNumber(), GeneralStatus.BLOCKED);
            } else if (card.getCardDTO().getCardNumber().equals(cardDTO.getCardNumber()) && cardDTO.getStatus().equals(GeneralStatus.BLOCKED)) {
                return userCardRepository.changeStatus(cardDTO.getCardNumber(), GeneralStatus.ACTIVE);
            }
        }
        return false;
    }

    public Boolean delete(String cardNumber) {
        if (!CardUtil.isValidate(cardNumber)) {
            return false;
        }

        CardDTO cardDTO = cardRepository.getCardByCardNumber(cardNumber);
        if (cardDTO == null) {
            return false;
        }
         var lists = cardLists();
         for (ProfileCard card : lists) {
            if (card.getCardDTO().getCardNumber().equals(cardDTO.getCardNumber())) {
                return userCardRepository.delete(card.getCardDTO().getId());
            }
        }
        return false;
    }
}
