package rs.digit.bpts.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.digit.bpts.domain.Account;
import rs.digit.bpts.domain.Transfer;
import rs.digit.bpts.dto.AccountDTO;
import rs.digit.bpts.service.TransferService;

import java.util.List;

@RestController
@RequestMapping(value = "/transfer")
public class TransferRest {

    private final Logger logger = LoggerFactory.getLogger(TransferRest.class);

    @Autowired
    private TransferService service;

    @PostMapping("/funds")
    public Account transferFunds(@RequestBody @Validated AccountDTO dto){
        logger.info("Successful transfer from {} to {} for amount of:{}", dto.sourceAccount(), dto.targetAccount(), dto.amount());
        return service.transferFunds(dto);
    }


    @GetMapping("/getAllTransfers")
    public List<Transfer> getAll(){
        return service.findAll();
    }
}



