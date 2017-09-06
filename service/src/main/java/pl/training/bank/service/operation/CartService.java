package pl.training.bank.service.operation;

import lombok.Setter;
import pl.training.bank.api.OperationsCart;
import pl.training.bank.entity.Operation;
import pl.training.bank.operation.OperationDto;
import pl.training.bank.service.Mapper;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Setter
@Stateful
public class CartService implements OperationsCart {

    @EJB
    private OperationsExecutorService operationsExecutorService;
    @EJB
    private Mapper mapper;
    private List<OperationDto> operationsDtos = new ArrayList<>();

    @Override
    public void add(OperationDto operationDto) {
        operationsDtos.add(operationDto);
    }

    @Remove
    @Override
    public void submit() {
        List<Operation> operations = mapper.map(operationsDtos, Operation.class);
        operationsExecutorService.submit(operations);
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
