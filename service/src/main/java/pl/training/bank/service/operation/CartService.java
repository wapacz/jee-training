package pl.training.bank.service.operation;

import pl.training.bank.api.OperationCart;
import pl.training.bank.entity.Operation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateful
public class CartService implements OperationCart {

    @Resource(lookup = "java:jboss/exported/jms/RemoteConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(lookup = "java:jboss/exported/jms/queue/Bank")
    private Queue queue;

    private List<Operation> operations = new ArrayList<>();

    @Override
    public void add(Operation operation) {
        operations.add(operation);
    }

    @Remove
    @Override
    public void submit() {
        try (JMSContext jmsContext = connectionFactory.createContext()) {
            JMSProducer jmsProducer = jmsContext.createProducer();
            operations.forEach(operation -> jmsProducer.send(queue, operation));
        }
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
