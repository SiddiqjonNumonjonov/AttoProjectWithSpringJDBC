package dasturlash.uz.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
    public void start() {
        printMenu();
    }
    public void printMenu() {
        System.out.println("***Admin Menu***");
    }
}
