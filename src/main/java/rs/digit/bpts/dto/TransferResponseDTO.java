package rs.digit.bpts.dto;

import rs.digit.bpts.common.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferResponseDTO(
          Status status,
          String message,
          String sourceAccount,
          String targetAccount,
          BigDecimal amount,
          LocalDateTime date
) {}
