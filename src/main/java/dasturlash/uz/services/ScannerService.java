package dasturlash.uz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;
@Service
public class ScannerService {
    private Scanner scannerForStr;
    private Scanner scannerForDigit;


    public ScannerService(Scanner scannerForStr, Scanner scannerForDigit) {
        this.scannerForStr = new Scanner(System.in);
        this.scannerForDigit = new Scanner(System.in);
    }

    public Scanner getScannerForStr() {
        return scannerForStr;
    }

    public Scanner getScannerForDigit() {
        return scannerForDigit;
    }

    public ScannerService() {
    }
}
