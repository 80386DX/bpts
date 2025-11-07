package rs.digit.bpts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.digit.bpts.domain.Account;
import rs.digit.bpts.domain.Transfer;
import rs.digit.bpts.dto.TransferRequestDTO;
import rs.digit.bpts.dto.TransferResponseDTO;
import rs.digit.bpts.error.AccountNotFoundException;
import rs.digit.bpts.error.InsufficientFundsException;
import rs.digit.bpts.error.TransferNotFoundException;
import rs.digit.bpts.repos.AccountRepository;
import rs.digit.bpts.repos.TransferRepository;
import rs.digit.bpts.common.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferService {

    private final Logger logger = LoggerFactory.getLogger(TransferService.class);

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private TransferRepository transferRepo;

    @Transactional
    public TransferResponseDTO transferFunds(TransferRequestDTO dto){

        try {
            validateDto(dto);

            Account source = validateAccounts(dto);

            validateFunds(source,dto.amount());

            Account target = accountRepo.findById(dto.targetAccount()).orElseThrow();
            source.debit(dto.amount());
            target.credit(dto.amount());
            accountRepo.save(source);
            accountRepo.save(target);

            transferRepo.save(new Transfer(
                    null,
                    dto.amount(),
                    source.getId(),
                    target.getId(),
                    LocalDateTime.now()
            ));

            logger.info("Transfer SUCCESS from {} to {} (amount: {})",
                    source.getId(), target.getId(), dto.amount());

            return buildResponse(Status.SUCCESS, Status.SUCCESS.getMessage(), dto);

        } catch (RuntimeException e) {
            logger.error("Transfer REJECTED: {}", e.getMessage());
            return buildResponse(Status.REJECT, e.getMessage(), dto);
        }

    }


    private void validateDto(TransferRequestDTO dto){
        if(dto.sourceAccount() == null || dto.targetAccount() == null){
            throw new RuntimeException("Missing account ID in transfer request.");
        }
        if(dto.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Transfer amount must be positive.");
        }
    }

    private Account validateAccounts(TransferRequestDTO dto){
        Account source = accountRepo.findById(dto.sourceAccount())
                .orElseThrow(() -> new AccountNotFoundException("Source account not found: " + dto.sourceAccount()));
        accountRepo.findById(dto.targetAccount())
                .orElseThrow(() -> new AccountNotFoundException("Target account not found: " + dto.targetAccount()));
        return source;
    }

    private void validateFunds(Account source, BigDecimal amount) {
        if (source.getFunds().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds for account: " + source.getId());
        }
    }

    private TransferResponseDTO buildResponse(Status status, String message, TransferRequestDTO dto) {
        return new TransferResponseDTO(
                status,
                message,
                dto.sourceAccount(),
                dto.targetAccount(),
                dto.amount(),
                LocalDateTime.now()
        );
    }

    public List<Transfer> findAll(){
        return transferRepo.findAll();
    }


    public  Transfer findById(Long id){
        return transferRepo.findById(id).orElseThrow(() -> new TransferNotFoundException(id));
    }

}