package dasturlash.uz.controllers;

import dasturlash.uz.containers.ComponentContainer;
import dasturlash.uz.services.ScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
    @Autowired
    private ScannerService scannerService;
    @Autowired
    private ComponentContainer componentContainer;
    public void start() {
        boolean loop = true;
        while (loop) {
            printMenu();
            int command = componentContainer.getAction();
            switch (command) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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
        System.out.print("choose one of them : ");

    }
}