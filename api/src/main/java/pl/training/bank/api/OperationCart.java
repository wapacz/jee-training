package pl.training.bank.api;

import pl.training.bank.entity.Operation;

import javax.ejb.Remote;

@Remote
public interface OperationCart {

    void add(Operation operation);

    void submit();

    void cancel();

}
