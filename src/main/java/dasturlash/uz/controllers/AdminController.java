package dasturlash.uz.controllers;

import dasturlash.uz.containers.ComponentContainer;
import dasturlash.uz.dtos.CardDTO;
import dasturlash.uz.services.CardService;
import dasturlash.uz.services.ScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class AdminController {
    @Autowired
    private ScannerService scannerService;
    @Autowired
    private ComponentContainer componentContainer;
    @Autowired
    private CardService cardService;
    public void start() {
        boolean loop = true;
        while (loop) {
            printMenu();
            int command = componentContainer.getAction();
            switch (command) {
                case 1:
                    createCard();
                    break;
                case 2:
                    cardLists();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    changeCardStatus();
                    break;
                case 5:
                    deleteCard();
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    break;
                case 16:
                    break;
                case 17:
                    break;
                case 18:
                    break;
                case 19:
                    break;
            }
        }

    }

    private void deleteCard() {
        System.out.println("enter card number : ");
        String cardNumber = scannerService.getScannerForStr().nextLine();
       var isDeleted =  cardService.deleteCard(cardNumber);
       if(isDeleted) {
           System.out.println("deleted successfully");
       }else {
           System.out.println("something went wrong !!!!");
       }
    }

    private void changeCardStatus() {

        System.out.println("enter card number : ");
        String cardNumber = scannerService.getScannerForStr().nextLine();

       var isChanged = cardService.changeStatus(cardNumber);
       if(isChanged) {
           System.out.println("Status changed successfully");
       }else{
           System.out.println("something went wrong !!!");
       }
    }

    private void update() {
        System.out.println("enter card number : ");
        String cardNumber = scannerService.getScannerForStr().nextLine();

        System.out.println("enter new expired date : ");
        String expiredDate = scannerService.getScannerForStr().nextLine();

        var isUpdated = cardService.update(cardNumber,expiredDate);
        if(isUpdated) {
            System.out.println("updated successfully");
        }else {
            System.out.println("something went wrong !!!");
        }
    }

    private void cardLists() {
        var lists = cardService.formattedLists();
        if(lists == null) {
            System.err.println("no card");
        }else {
            for (CardDTO cardDTO : lists) {
                System.out.println(cardDTO.getId() + " "+cardDTO.getCardNumber() +" "+cardDTO.getBalance()+ " "+cardDTO.getExpiredDate()+ " "+ cardDTO.getCreatedAt());
            }
        }
    }

    private void createCard() {
        System.out.println("enter number : ");
        String cardNumber = scannerService.getScannerForStr().nextLine();

        System.out.println("enter expired date (yyy-mm-dd)");
        String date = scannerService.getScannerForStr().nextLine();

       var isAdded = cardService.addCard(cardNumber,date);
       if(isAdded) {
           System.out.println("added successfully");
       }else {
           System.out.println("something went wrong !!!");
       }
    }

    public void printMenu() {
        System.out.println("***Admin Menu***");
        System.out.println("1=>Create card");
        System.out.println("2=>Card lists");
        System.out.println("3=>Update card");
        System.out.println("4=>change card status");
        System.out.println("5=>delete card");
        System.out.println("6=>create terminal");
        System.out.println("7=>terminal lists");
        System.out.println("8=>update terminal");
        System.out.println("9=>change status terminal");
        System.out.println("10=>delete terminal");
        System.out.println("11=>transaction list");
        System.out.println("12=>company card balance");
        System.out.println("13=>today's transactions");
        System.out.println("14=> daily transactions");
        System.out.println("15=>Mid transactions");
        System.out.println("16=>transaction by terminal");
        System.out.println("17=>transaction by card");
        System.out.println("18=>Profile lists");
        System.out.println("19=>change profile status");


    }
}