package dasturlash.uz.controllers;

import dasturlash.uz.containers.ComponentContainer;
import dasturlash.uz.services.ScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
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
                case 0:
                    loop = false;
                     break;
            }
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
