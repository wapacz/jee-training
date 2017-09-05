package pl.training.bank.service.operation;

import lombok.Setter;
import pl.training.bank.api.OperationCart;
import pl.training.bank.entity.Operation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Setter
@Stateful
public class CartService implements OperationCart {

    @EJB
    private OperationExecutorService executorService;
    private List<Operation> operations = new ArrayList<>();

    @Override
    public void add(Operation operation) {
        operations.add(operation);
    }

    @Remove
    @Override
    public void submit() {
        executorService.submit(operations);
    }

    @Remove
    @Override
    public void cancel() {
    }

    @PostConstruct
    public void init() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "CartService is ready...");
    }

    @PreDestroy
    public void destroy() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "CartService is going down...");
    }

    @PrePassivate
    public void prePassivate() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "CartService will be passivated...");
    }

    @PostActivate
    public void postActivate() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "CartService will be activated...");
    }

}
