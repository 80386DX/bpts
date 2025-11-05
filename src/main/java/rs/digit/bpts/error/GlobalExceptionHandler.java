package rs.digit.bpts.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import rs.digit.bpts.dto.TransferResponseDTO;
import rs.digit.bpts.common.Status;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<TransferResponseDTO> handleAccountNotFound(AccountNotFoundException ex) {
        return ResponseEntity
                .badRequest()
                .body(new TransferResponseDTO(Status.REJECT, ex.getMessage(), null, null, null, LocalDateTime.now()));
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<TransferResponseDTO> handleInsufficientFunds(InsufficientFundsException ex) {
        return ResponseEntity
                .badRequest()
                .body(new TransferResponseDTO(Status.REJECT, ex.getMessage(), null, null, null, LocalDateTime.now()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<TransferResponseDTO> handleValidationErrors(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        return ResponseEntity
                .badRequest()
                .body(new TransferResponseDTO(
                        Status.REJECT,
                        message,
                        null,
                        null,
                        null,
                        LocalDateTime.now()
                ));
    }
}
