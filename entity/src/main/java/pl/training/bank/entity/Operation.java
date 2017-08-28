package pl.training.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Operation implements Serializable {

    private Long id;
    private Date date;
    @NonNull
    private Account account;
    @NonNull
    private OperationType type;
    @NonNull
    private long funds;

}
