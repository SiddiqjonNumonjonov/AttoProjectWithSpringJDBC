package dasturlash.uz.controllers;

import dasturlash.uz.containers.ComponentContainer;
import dasturlash.uz.dtos.ProfileDTO;
import dasturlash.uz.dtos.TransactionDTO;
import dasturlash.uz.repositories.TableRepository;
import dasturlash.uz.services.AuthService;
import dasturlash.uz.services.InitService;
import dasturlash.uz.services.ScannerService;
import dasturlash.uz.services.TransactionService;
import dasturlash.uz.utills.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class MainController {
    @Autowired
    private ScannerService scannerService;
    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private InitService initService;
    @Autowired
    private AuthService authService;
    @Autowired
    private ComponentContainer componentContainer;
    @Autowired
    private TransactionService transactionService;

    public void start() {
        tableRepository.createTables();
        initService.initAdmin();
        boolean loop = true;
        while (loop) {
            printMenu();
            int action = componentContainer.getAction();

            switch (action) {
                case 1:
                    login();
                    break;
                case 2:
                    registration();
                    break;
                case 3:
                    makePayment();
                    break;
                case 0:
                    loop = false;
                    System.out.println("program is ended");
                    break;
                default:
                    System.err.println("something went wrong !!!");

            }
        }

    }

    private void makePayment() {
        System.out.println("enter card number : ");
        String cardNumber = scannerService.getScannerForStr().nextLine();

        System.out.println("enter terminal code  : ");
        String code = scannerService.getScannerForStr().nextLine();

       var isMadePayment = transactionService.makePayment(cardNumber,code);
       if(isMadePayment) {
           System.out.println("transaction is completed successfully");
       }else{
           System.out.println("something went wrong !!!");
       }
    }

    private void registration() {
        System.out.println("enter name :");
        String name = scannerService.getScannerForStr().nextLine();
        System.out.println("enter surname : ");
        String surname = scannerService.getScannerForStr().nextLine();
        System.out.println("enter phone : ");
        String phone = scannerService.getScannerForStr().nextLine();
        System.out.println("enter password : ");
        String password = scannerService.getScannerForStr().nextLine();

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setName(name);
        profileDTO.setSurname(surname);
        profileDTO.setPassword(MD5Util.encode(password));
        profileDTO.setPhone(phone);

        var isAdded = authService.add(profileDTO);
        if(isAdded) {
            System.out.println("added successfully");
        }else {
            System.err.println("something went wrong !!!");
        }
    }

    private void login() {
        System.out.println("enter phone : ");
        String phone = scannerService.getScannerForStr().nextLine();

        System.out.println("enter password : ");
        String password = scannerService.getScannerForStr().nextLine();


       var isLogged = authService.login(phone,password);
       if(!isLogged) {
           System.out.println("phone or password wrong !!!");
           System.exit(-1);
       }
    }

    public void printMenu() {
        System.out.println("***Main Menu ***");
        System.out.println("1=>Login");
        System.out.println("2=>Registration");
        System.out.println("3=>Make payment");
        System.out.println("0=> exit");
    }

}
