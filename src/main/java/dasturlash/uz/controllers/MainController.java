package dasturlash.uz.controllers;

import dasturlash.uz.repositories.TableRepository;
import dasturlash.uz.services.InitService;
import dasturlash.uz.services.ScannerService;
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
    public void start() {
        tableRepository.createTables();
        initService.initAdmin();
        boolean loop = true;
        while (loop) {
            printMenu();
            int action = getAction();

            switch (action) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
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
    public void printMenu() {
        System.out.println("***Main Menu ***");
        System.out.println("1=>Login");
        System.out.println("2=>Registration");
        System.out.println("3=>Make payment");
        System.out.println("0=> exit");
        System.out.print("enter action : ");
    }
    public int getAction() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
//        return scannerService.getScannerForDigit().nextInt();
    }

}
