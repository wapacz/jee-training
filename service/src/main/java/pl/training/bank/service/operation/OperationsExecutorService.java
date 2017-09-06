package pl.training.bank.service.operation;

import pl.training.bank.entity.Operation;
import pl.training.bank.operation.OperationDto;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class OperationsExecutorService {

    @Resource(lookup = "java:jboss/exported/jms/RemoteConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(lookup = "java:jboss/exported/jms/queue/Bank")
    private Queue queue;

    public void submit(List<OperationDto> operations) {
        try (JMSContext jmsContext = connectionFactory.createContext()) {
            JMSProducer jmsProducer = jmsContext.createProducer();
            operations.forEach(operation -> jmsProducer.send(queue, operation));
        }
    }

    @PostConstruct
    public void init() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "OperationsExecutorService is ready...");
    }

    @PreDestroy
    public void destroy() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "OperationsExecutorService is going down...");
    }

}
