package pl.training.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQuery(name = User.GET_BY_LOGIN_QL, query = "select u from User u where u.login = :login")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    public static final String GET_BY_LOGIN_QL = "getUserByLogin";

    @GeneratedValue
    @Id
    private Long id;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;

}
