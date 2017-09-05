package pl.training.bank.rest.dto;

import lombok.Data;
import pl.training.bank.entity.OperationType;
import pl.training.bank.validator.Unsigned;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
public class OperationDto implements Serializable {

    @Valid
    private AccountDto account;
    private OperationType type;
    @Unsigned
    @Min(1_000)
    private long funds;

}
