package pl.training.bank.service.security;

import lombok.Setter;
import pl.training.bank.entity.User;
import pl.training.bank.security.UserNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Setter
@Stateless
public class JpaUserRepositoryService {

    @PersistenceContext(name = "bank")
    private EntityManager entityManager;

    public User save(User user) {
        entityManager.persist(user);
        entityManager.flush();
        entityManager.refresh(user);
        return user;
    }

    public User getByLogin(String login) {
        try {
            return entityManager.createNamedQuery(User.GET_BY_LOGIN_QL, User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new UserNotFoundException();
        }
    }

}
