package dasturlash.uz.services;

import dasturlash.uz.dtos.CardDTO;
import dasturlash.uz.dtos.ProfileDTO;
import dasturlash.uz.enums.GeneralStatus;
import dasturlash.uz.repositories.CardRepository;
import dasturlash.uz.utills.CardUtil;
import dasturlash.uz.utills.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public Boolean addCard(String cardNumber, String expiredDate) {
        CardDTO cardDTO = cardRepository.getCardByCardNumber(cardNumber);
        if(cardDTO != null) {
            return false;
        }

        LocalDate localDate = getExpiredDate(expiredDate);

        if(localDate == null && !isValidate(cardNumber)) {
            return false;
        }

        CardDTO card = new CardDTO();
        card.setCardNumber(cardNumber);
        card.setBalance(0d);
        card.setStatus(GeneralStatus.ACTIVE);
        card.setCreatedAt(LocalDateTime.now());
        card.setVisible(true);
        card.setExpiredDate(String.valueOf(localDate));

       return cardRepository.add(card);
    }
    public LocalDate getExpiredDate(String date) {
        String [] strings = date.split("/");
        int month = Integer.parseInt(strings[0]);
        int year = Integer.parseInt("20" + strings[1]);
       return LocalDate.of(year,month,1);
    }
    public Boolean isValidate(String cardNumber) {
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

    public List<CardDTO> cardLists() {
       var lists = cardRepository.cardLists();
       if(lists.isEmpty()) {
           return null;
       }
       return lists;
    }

    public List<CardDTO> formattedLists() {
        List<CardDTO> formattedList = new ArrayList<>();
        var lists = cardRepository.cardLists();
        if(lists .isEmpty()) {
            return null;
        }
        for (CardDTO cardDTO : lists) {
            CardDTO card = new CardDTO();
            card.setVisible(cardDTO.getVisible());
            card.setStatus(cardDTO.getStatus());
            card.setBalance(cardDTO.getBalance());
            card.setId(cardDTO.getId());
            card.setExpiredDate(DateUtil.toDateAndYearString(LocalDate.parse(cardDTO.getExpiredDate())));
            card.setCardNumber(CardUtil.replaceWithStar(cardDTO.getCardNumber()));
            card.setCreatedAt(LocalDateTime.parse(DateUtil.toSimpleFormat(cardDTO.getCreatedAt())));
            formattedList.add(card);
        }
        return formattedList;
    }

    public Boolean update(String cardNumber, String expiredDate) {
        CardDTO cardDTO = cardRepository.getCardByCardNumber(cardNumber);
        if(cardDTO == null) {
            return false;
        }
         LocalDate localDate = getExpiredDate(expiredDate);
       return cardRepository.update(cardNumber,localDate);
    }

    public Boolean changeStatus(String cardNumber) {

        if(!isValidate(cardNumber)) {
            return false;
        }
        CardDTO cardDTO = cardRepository.getCardByCardNumber(cardNumber);

        if(cardDTO == null) {
            return false;
        }

        if(cardDTO.getStatus().equals(GeneralStatus.ACTIVE)) {
            return cardRepository.changeStatus(cardNumber,GeneralStatus.BLOCKED);
        }
        return cardRepository.changeStatus(cardNumber,GeneralStatus.ACTIVE);
    }

    public Boolean deleteCard(String cardNumber) {

        CardDTO cardDTO = cardRepository.getCardByCardNumber(cardNumber);
        if(cardDTO == null) {
            return false;
        }
        return cardRepository.delete(cardNumber);
    }
}
