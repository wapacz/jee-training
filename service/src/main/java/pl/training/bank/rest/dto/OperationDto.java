package pl.training.bank.rest.dto;

import lombok.Data;
import pl.training.bank.entity.OperationType;

@Data
public class OperationDto {

    private AccountDto account;
    private OperationType type;
    private long funds;

}
