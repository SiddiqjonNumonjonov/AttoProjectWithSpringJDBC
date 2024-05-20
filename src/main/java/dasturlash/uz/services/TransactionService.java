package dasturlash.uz.services;

import dasturlash.uz.dtos.TransactionDTO;
import dasturlash.uz.enums.GeneralStatus;
import dasturlash.uz.enums.TransactionType;
import dasturlash.uz.repositories.CardRepository;
import dasturlash.uz.repositories.TerminalRepository;
import dasturlash.uz.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TerminalRepository terminalRepository;

    public Boolean createTransaction(Integer cardId, Double amount, Integer terminalId, TransactionType transactionType) {

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(amount);
        transactionDTO.setCreatedAt(LocalDateTime.now());
        transactionDTO.setCardId(cardId);
        transactionDTO.setType(transactionType);
        transactionDTO.setTerminalId(null);

        return transactionRepository.create(transactionDTO);
    }

    public Boolean makePayment(String cardNumber, String code) {
        var card = cardRepository.getCardByCardNumber(cardNumber);
        var terminal = terminalRepository.getTerminalByCode(code);

        if(card == null || terminal == null) {
            return false;
        }
        if(!card.getVisible() || !terminal.getVisible()) {
            return false;
        }
        if(card.getStatus().equals(GeneralStatus.BLOCKED) || terminal.getStatus().equals(GeneralStatus.BLOCKED)) {
            return false;
        }

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(1700d);
        if(card.getBalance() < transactionDTO.getAmount()) {
            return false;
        }
        transactionDTO.setTerminalId(terminal.getId());
        transactionDTO.setCardId(card.getId());
        transactionDTO.setType(TransactionType.PAYMENT);
        transactionDTO.setCreatedAt(LocalDateTime.now());

        LocalDateTime time = LocalDateTime.now().minusMinutes(1);
        if(transactionDTO.getCreatedAt().isAfter(time)) {
            cardRepository.minusBalance(cardNumber,transactionDTO.getAmount());
           return transactionRepository.create(transactionDTO);

        }
        return false;
    }
}
