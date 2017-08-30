package pl.training.bank.service;

import lombok.Setter;
import pl.training.bank.api.Bank;
import pl.training.bank.entity.Account;
import pl.training.bank.service.account.AccountService;
import pl.training.bank.service.operation.ReportService;

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
    private ReportService reportService;
    @EJB
    private AccountService accountService;

    @Override
    public Account createAccount() {
        return accountService.createAccount();
    }

    @Override
    public void deposit(long funds, String accountNumber) {
        accountService.deposit(funds, accountNumber);
    }

    @Override
    public void withdraw(long funds, String accountNumber) {
        accountService.withdraw(funds, accountNumber);
    }

    @Override
    public void transfer(long funds, String sourceAccountNumber, String destinationAccountNumber) {
        accountService.withdraw(funds, sourceAccountNumber);
        accountService.deposit(funds, destinationAccountNumber);
    }

    @Override
    public long getBalance(String accountNumber) {
        return accountService.getBalance(accountNumber);
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
