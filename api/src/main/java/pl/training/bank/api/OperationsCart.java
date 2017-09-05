package pl.training.bank.api;

import pl.training.bank.entity.Operation;

import javax.ejb.Remote;

@Remote
public interface OperationsCart {

    void add(Operation operation);

    void submit();

    void cancel();

}
