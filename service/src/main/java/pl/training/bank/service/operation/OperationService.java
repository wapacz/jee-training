package pl.training.bank.service.operation;

import lombok.Setter;
import pl.training.bank.entity.Operation;
import pl.training.bank.service.account.AccountService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.logging.Level;
import java.util.logging.Logger;

@Setter
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "BankDS"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class OperationService implements MessageListener {

    @EJB
    private AccountService accountService;

    @Override
    public void onMessage(Message message) {
        try {
            Operation operation = message.getBody(Operation.class);
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Processing new operation: " + operation);
            switch (operation.getType()) {
                case DEPOSIT:
                    accountService.deposit(operation.getFunds(), operation.getAccount().getNumber());
                    break;
                case WITHDRAW:
                    accountService.withdraw(operation.getFunds(), operation.getAccount().getNumber());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operation");
            }
        } catch (JMSException e) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, e.getMessage());
        }
    }

    @PostConstruct
    public void init() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "OperationService is ready...");
    }

    @PreDestroy
    public void destroy() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "OperationService is going down...");
    }


}
