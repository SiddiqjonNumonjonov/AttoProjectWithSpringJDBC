package dasturlash.uz.services;

import dasturlash.uz.dtos.TerminalDTO;
import dasturlash.uz.enums.GeneralStatus;
import dasturlash.uz.repositories.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TerminalService {

    @Autowired
    private TerminalRepository terminalRepository;
    public Boolean create(String code, String address) {
        if(isExist(code) == null) {
            return false;
        }
        if(!isValidate(address)) {
            return false;
        }

        TerminalDTO terminal = new TerminalDTO();
        terminal.setAddress(address);
        terminal.setCode(code);
        terminal.setCreatedAt(LocalDateTime.now());
        terminal.setStatus(GeneralStatus.ACTIVE);
        terminal.setVisible(true);

        return terminalRepository.create(terminal);
    }

    public List<TerminalDTO> terminalLists() {
       var terminalLists =  terminalRepository.terminalLists();
       if(terminalLists.isEmpty()) {
           return null;
       }
       return terminalLists;
    }

    public Boolean update(String code, String address) {

        if(!isValidate(address)) {
            return false;
        }

        if(isExist(code) == null) {
            return false;
        }
        return terminalRepository.update(code,address);
    }
    public Boolean isValidate(String address) {
        if(address == null || address.isBlank() || address.trim().length() < 2) {
            return false;
        }
        return true;
    }

    public Boolean changStatus(String code) {
        TerminalDTO terminalDTO = isExist(code);
          if(terminalDTO == null) {
              return false;
          }

        if(terminalDTO.getStatus().equals(GeneralStatus.ACTIVE)) {
           return terminalRepository.changeStatus(code,GeneralStatus.BLOCKED);
        }

        return terminalRepository.changeStatus(code,GeneralStatus.ACTIVE);

    }

    public Boolean delete(String  code) {
        if(isExist(code) == null) {
            return null;
        }
       return terminalRepository.delete(code);
    }
    public TerminalDTO isExist(String code) {
        return terminalRepository.getTerminalByCode(code);
    }
}
