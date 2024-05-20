package dasturlash.uz.containers;

import dasturlash.uz.dtos.ProfileDTO;
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
                System.out.print("choose one of them : ");
                scannerService = new ScannerService();
                return scannerService.getScannerForDigit().nextInt();
            }catch (InputMismatchException e) {
                System.err.println("incorrect input");
            }
        }
    }
        public ProfileDTO currentProfile;
}
