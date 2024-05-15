package dasturlash.uz.containers;

import dasturlash.uz.services.ScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;

@Component
public class ComponentContainer {
    @Autowired
    private ScannerService scannerService;
    public Integer getAction() {
        while (true) {
            try{
                return scannerService.getScannerForDigit().nextInt();
            }catch (InputMismatchException exception) {
                System.out.println("Wrong input");
            }
        }
    }
}
