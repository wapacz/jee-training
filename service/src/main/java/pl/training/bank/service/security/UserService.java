package pl.training.bank.service.security;

import lombok.Setter;
import pl.training.bank.entity.User;
import pl.training.bank.rest.jwt.PasswordEncoder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Setter
@Stateless
public class UserService {

    @EJB
    private JpaUserRepositoryService userRepository;
    @Inject
    private PasswordEncoder passwordEncoder;

    public void addUser(User user) {
        encodePassword(user);
        userRepository.save(user);
    }

    public User getUserByLogin(String login) {
        return userRepository.getByLogin(login);
    }

    private void encodePassword(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
    }

}
