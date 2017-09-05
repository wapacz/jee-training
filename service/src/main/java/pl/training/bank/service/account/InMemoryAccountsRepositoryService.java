package pl.training.bank.service.account;

import pl.training.bank.account.AccountNotFoundException;
import pl.training.bank.entity.Account;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class InMemoryAccountsRepositoryService implements AccountsRepository {

    private long counter;
    private Map<String, Account> accounts = new HashMap<>();

    @Lock(LockType.READ)
    @Override
    public Account getByNumber(String number) {
        Account account = accounts.get(number);
        if (account == null) {
            throw new AccountNotFoundException();
        }
        return account;
    }

    @Lock(LockType.READ)
    @Override
    public Account getById(Long id) {
            return accounts.values().stream()
                .filter(account -> account.getId().equals(id))
                .findFirst()
                .orElseThrow(AccountNotFoundException::new);
    }

    @Override
    public void update(Account account) {
        String accountNumber = account.getNumber();
        getByNumber(accountNumber);
        accounts.put(accountNumber, account);
    }

    @Override
    public Account save(Account account) {
        account.setId(++counter);
        accounts.put(account.getNumber(), account);
        return account;
    }

    @PostConstruct
    public void init() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "InMemoryAccountsRepositoryService is ready...");
    }

    @PreDestroy
    public void destroy() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "InMemoryAccountsRepositoryService is going down...");
    }

}
