package rs.digit.bpts.rest;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.digit.bpts.domain.Transfer;
import rs.digit.bpts.dto.TransferRequestDTO;
import rs.digit.bpts.dto.TransferResponseDTO;
import rs.digit.bpts.service.TransferService;

import java.util.List;

@RestController
@RequestMapping(value = "/transfers")
public class TransferRest {

    private final Logger logger = LoggerFactory.getLogger(TransferRest.class);

    @Autowired
    private TransferService service;

    @PostMapping("/new")
    public TransferResponseDTO transferFunds(@RequestBody @Valid TransferRequestDTO dto){
        logger.info("Successful transfer from {} to {} for amount of:{}", dto.sourceAccount(), dto.targetAccount(), dto.amount());
        return service.transferFunds(dto);
    }


    @GetMapping("/history")
    public List<Transfer> getAll(){
        return service.findAll();
    }


    @GetMapping("/history/{id}")
    public Transfer getTransferById(@PathVariable Long id) {
        return service.findById(id);
    }

}



