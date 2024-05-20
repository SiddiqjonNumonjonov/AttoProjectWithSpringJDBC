package dasturlash.uz.controllers;

import dasturlash.uz.containers.ComponentContainer;
import dasturlash.uz.dtos.ProfileCard;
import dasturlash.uz.services.ScannerService;
import dasturlash.uz.services.UserCardService;
import dasturlash.uz.utills.CardUtil;
import dasturlash.uz.utills.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class UserController {
    @Autowired
    private ScannerService scannerService;
    @Autowired
    private ComponentContainer componentContainer;
    @Autowired
    private UserCardService userCardService;

    public void start() {
        boolean loop = true;
        while (loop) {
            printMenu();
            int command = componentContainer.getAction();
            switch (command) {
                case 1:
                    addCard();
                    break;
                case 2:
                    cardLists();
                    break;
                case 3:
                    changeCardStatus();
                    break;
                case 4:
                    deleteCard();
                    break;
                case 5:
                    refill();
                    break;
                case 6:
                    break;
                case 0:
                    loop = false;
                     break;
            }
        }
    }

    private void refill() {
        System.out.println("enter card number : ");
        String cardNumber = scannerService.getScannerForStr().nextLine();

        System.out.println("enter balance : ");
        Double amount = scannerService.getScannerForDigit().nextDouble();

        var isRefilled = userCardService.refill(cardNumber,amount);
        if(isRefilled) {
            System.out.println("refilled successfully");
        }else {
            System.out.println("something went wrong !!!");
        }
    }

    private void deleteCard() {
        System.out.println("enter card number : ");
        String cardNumber = scannerService.getScannerForStr().nextLine();

       var isDeleted = userCardService.delete(cardNumber);
       if(isDeleted) {
           System.out.println("deleted successfully");
       }else {
           System.out.println("something went wrong !!!");
       }
    }

    private void changeCardStatus() {
        System.out.println("enter card number : ");
        String cardNumber = scannerService.getScannerForStr().nextLine();

       var isChanged = userCardService.changeCardStatus(cardNumber);
       if(isChanged) {
           System.out.println("changed successfully");
       }else {
           System.out.println("something went wrong !!!");
       }
    }

    private void cardLists() {
      var cardLists  = userCardService.cardLists();
      if(cardLists.isEmpty()) {
          System.err.println("no card");
      }else {
          for (ProfileCard profileCard : cardLists) {
              System.out.println(profileCard.getId()+" "+ CardUtil.replaceWithStar(profileCard.getCardDTO().getCardNumber()) +" "+
                      profileCard.getCardDTO().getBalance()+" "+
                      DateUtil.toDateAndYearString(LocalDate.parse(profileCard.getCardDTO().getExpiredDate())) + " "+
                      DateUtil.toSimpleFormat(profileCard.getCreatedAt()));
          }
      }
    }

    private void addCard() {
        System.out.println("enter card number : ");
        String cardNumber = scannerService.getScannerForStr().nextLine();

        var isConnected = userCardService.addToCard(cardNumber);
        if(isConnected) {
            System.out.println("added successfully");
        }else {
            System.err.println("something went wrong !!!");
        }
    }

    public void printMenu() {
        System.out.println("***User Menu***");
        System.out.println("1=>Add card");
        System.out.println("2=>Card lists");
        System.out.println("3=>Card change status");
        System.out.println("4=>Delete card");
        System.out.println("5=>Refill");
        System.out.println("6=>Transaction");
        System.out.println("0=>exit");
        System.out.print("choose one of them : ");
    }
}
