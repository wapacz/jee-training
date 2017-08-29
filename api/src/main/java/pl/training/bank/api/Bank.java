package pl.training.bank.api;

import pl.training.bank.entity.Account;
import pl.training.bank.entity.OperationSummary;

import javax.ejb.Remote;
import java.util.List;
import java.util.concurrent.Future;

@Remote
public interface Bank {

    Account createAccount();

    void deposit(long funds, String accountNumber);

    void withdraw(long funds, String accountNumber);

    void transfer(long funds, String sourceAccountNumber, String destinationAccountNumber);

    long getBalance(String accountNumber);

    Future<List<OperationSummary>> generateOperationsReport();

}
