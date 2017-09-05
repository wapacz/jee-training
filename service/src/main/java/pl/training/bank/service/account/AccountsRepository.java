package pl.training.bank.service.account;

import pl.training.bank.entity.Account;

import javax.ejb.Local;

@Local
public interface AccountsRepository {

    Account getByNumber(String number);

    Account getById(Long id);

    void update(Account account);

    Account save(Account account);

}
