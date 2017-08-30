package pl.training.bank.api;

import pl.training.bank.entity.Account;

import javax.ejb.Remote;
import javax.jws.WebService;

@WebService(portName = "Bank", serviceName = "Bank")
@Remote
public interface Bank {

    Account createAccount();

    void deposit(long funds, String accountNumber);

    void withdraw(long funds, String accountNumber);

    void transfer(long funds, String sourceAccountNumber, String destinationAccountNumber);

    long getBalance(String accountNumber);

}
