package rs.digit.bpts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.digit.bpts.domain.Account;
import rs.digit.bpts.domain.Transfer;
import rs.digit.bpts.dto.AccountDTO;
import rs.digit.bpts.repos.AccountRepository;
import rs.digit.bpts.repos.TransferRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransferService {

    private final Logger logger = LoggerFactory.getLogger(TransferService.class);

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private TransferRepository transferRepo;

    @Transactional
    public Account transferFunds(AccountDTO dto){
        if(!checkAccounts(dto)){
            logger.warn("Account not found {}", dto.sourceAccount());
            throw new RuntimeException("Account not found");
        }

        if(!checkFunds(dto)){
            logger.warn("Insufficient funds from account: {}", dto.sourceAccount());
            throw new RuntimeException("Insufficient funds for transfer.");
        }


        Account sourceAccount = accountRepo.findById(dto.sourceAccount()).orElse(null);

        Account destinationAccount = accountRepo.findById(dto.targetAccount()).orElse(null);


        sourceAccount.debit(dto.amount());
        destinationAccount.credit(dto.amount());

        accountRepo.save(sourceAccount);
        accountRepo.save(destinationAccount);

        Transfer transfer = new Transfer(null, dto.amount(), sourceAccount.getId(), destinationAccount.getId(), LocalDateTime.now());
        transferRepo.save(transfer);

        logger.info("Successfully transferred {} from account {} to account {}", dto.amount(), dto.sourceAccount(), dto.targetAccount());

        return destinationAccount;
    }


    public boolean checkFunds(AccountDTO dto){
        if (!checkAccounts(dto)) {
            return false;
        }

        Optional<Account> sourceAccountOpt = accountRepo.findById(dto.sourceAccount());

        if (sourceAccountOpt.isPresent()) {
            Account sourceAccount = sourceAccountOpt.get();
            return sourceAccount.getFunds().compareTo(dto.amount()) >= 0;
        }

        return false;
    }



    public boolean checkAccounts(AccountDTO dto){
        return dto.sourceAccount() != null && dto.targetAccount() != null;
    }


    public List<Transfer> findAll(){
        return transferRepo.findAll();
    }

}