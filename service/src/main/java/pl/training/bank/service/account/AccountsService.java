package pl.training.bank.service.account;

import lombok.Setter;
import pl.training.bank.account.InsufficientFundsException;
import pl.training.bank.entity.Account;
import pl.training.bank.service.operation.DepositLimitInterceptor;
import pl.training.bank.service.operation.OperationHistoryInterceptor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.util.logging.Level;
import java.util.logging.Logger;

@Setter
@Stateless
public class AccountsService {

    @EJB
    private AccountNumberGenerator accountNumberGenerator;
    @EJB(beanName = "JpaAccountsRepositoryService")
    private AccountsRepository accountsRepository;

    public Account createAccount() {
        String accountNumber = accountNumberGenerator.getNext();
        Account account = new Account(accountNumber);
        accountsRepository.save(account);
        return account;
    }

    @Interceptors({OperationHistoryInterceptor.class, DepositLimitInterceptor.class})
    public void deposit(long funds, String accountNumber) {
        Account account = accountsRepository.getByNumber(accountNumber);
        account.deposit(funds);
    }

    @Interceptors(OperationHistoryInterceptor.class)
    public void withdraw(long funds, String accountNumber) {
        Account account = accountsRepository.getByNumber(accountNumber);
        checkFunds(funds, account);
        account.withdraw(funds);
    }

    private void checkFunds(long funds, Account account) {
        if (account.getBalance() < funds) {
            throw new InsufficientFundsException();
        }
    }

    public Account getAccountById(Long id) {
        return accountsRepository.getById(id);
    }

    public long getBalance(String accountNumber) {
        return accountsRepository.getByNumber(accountNumber).getBalance();
    }

    @PostConstruct
    public void init() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "AccountsService is ready...");
    }

    @PreDestroy
    public void destroy() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "AccountsService is going down...");
    }

}
