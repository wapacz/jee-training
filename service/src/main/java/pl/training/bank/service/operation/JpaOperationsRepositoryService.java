package pl.training.bank.service.operation;

import lombok.Setter;
import pl.training.bank.entity.Operation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Level;
import java.util.logging.Logger;

@Setter
@Stateless
public class JpaOperationsRepositoryService {

    @PersistenceContext(unitName = "bank")
    private EntityManager entityManager;

    public void save(Operation operation) {
        entityManager.persist(operation);
    }

    @PostConstruct
    public void init() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "JpaOperationsRepositoryService is ready...");
    }

    @PreDestroy
    public void destroy() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "JpaOperationsRepositoryService is going down...");
    }

}
