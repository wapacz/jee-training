package pl.training.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = Account.GET_BY_NUMBER_QL, query = "select a from Account a where a.number = :number")
@Table(name = "accounts")
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Account implements Serializable {

    public static final String GET_BY_NUMBER_QL = "getAccountByNumber";

    @GeneratedValue
    @Id
    private Long id;
    @Column(unique = true)
    @NonNull
    private String number;
    private long balance;

    public void deposit(long funds) {
        balance += funds;
    }

    public void withdraw(long funds) {
        balance -= funds;
    }

}
