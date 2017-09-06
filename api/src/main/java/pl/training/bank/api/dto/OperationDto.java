package pl.training.bank.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.training.bank.validator.Unsigned;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.Serializable;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class OperationDto implements Serializable {

    @Valid
    @NonNull
    private AccountDto primaryAccount;
    private AccountDto secondaryAccount;
    @NonNull
    private OperationTypeDto type;
    @Unsigned
    @Min(1_000)
    @NonNull
    private Long funds;

}
