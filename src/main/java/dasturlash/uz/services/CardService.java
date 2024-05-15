package dasturlash.uz.services;

import dasturlash.uz.dtos.CardDTO;
import dasturlash.uz.enums.GeneralStatus;
import dasturlash.uz.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        card.setExpiredDate(localDate);

       return cardRepository.add(card);
    }
    public LocalDate getExpiredDate(String date) {
        String [] strings = date.split("/");
        int month = Integer.parseInt(strings[0]);
        int year = Integer.parseInt("20", Integer.parseInt(strings[1]));
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
}
