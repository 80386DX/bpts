package rs.digit.bpts.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

//@Getter
//@AllArgsConstructor
public record AccountDTO(@NotNull(message = "Source account id cannot be null") String sourceAccount,
                         @NotNull(message = "Target account id cannot be null") String targetAccount,
                         @NotNull(message = "Amount cannot be null") BigDecimal amount) {

    public AccountDTO {
        if (sourceAccount == null) {
            throw new IllegalArgumentException("Source account id cannot be null");
        }
        if (targetAccount == null) {
            throw new IllegalArgumentException("Target account id cannot be null");
        }
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
    }


//    @NotNull(message = "Source account id cannot be null")
//    private final String sourceAccount;
//
//    @NotNull(message = "Target account id cannot be null")
//    private final String targetAccount;
//
//    @NotNull(message = "Amount cannot be null")
//    private final BigDecimal amount;

}
