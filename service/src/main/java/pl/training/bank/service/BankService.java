package pl.training.bank.service;

import lombok.Setter;
import pl.training.bank.api.Bank;
import pl.training.bank.entity.Account;
import pl.training.bank.service.account.AccountsService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(portName = "Bank", serviceName = "Bank")
@Setter
@Stateless
public class BankService implements Bank {

    @EJB
    private AccountsService accountsService;

    @Override
    public Account createAccount() {
        return accountsService.createAccount();
    }

    @Override
    public void deposit(long funds, String accountNumber) {
        accountsService.deposit(funds, accountNumber);
    }

    @Override
    public void withdraw(long funds, String accountNumber) {
        accountsService.withdraw(funds, accountNumber);
    }

    @Override
    public void transfer(long funds, String sourceAccountNumber, String destinationAccountNumber) {
        accountsService.withdraw(funds, sourceAccountNumber);
        accountsService.deposit(funds, destinationAccountNumber);
    }

    @Override
    public long getBalance(String accountNumber) {
        return accountsService.getBalance(accountNumber);
    }

    @PostConstruct
    public void init() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "BankService is ready...");
    }

    @PreDestroy
    public void destroy() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "BankService is going down...");
    }

}
