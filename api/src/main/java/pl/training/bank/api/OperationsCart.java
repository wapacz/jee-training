package pl.training.bank.api;

import pl.training.bank.api.dto.OperationDto;

import javax.ejb.Remote;

@Remote
public interface OperationsCart {

    void add(OperationDto operation);

    void submit();

    void cancel();

}
