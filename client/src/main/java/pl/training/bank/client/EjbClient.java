package pl.training.bank.client;

import pl.training.bank.api.Bank;
import pl.training.bank.api.OperationCart;
import pl.training.bank.entity.Account;
import pl.training.bank.entity.Operation;
import pl.training.bank.entity.OperationType;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.NamingException;

public class EjbClient {

    private static final String OPERATION_CART_JNDI_NAME = "java:/bank/CartService!pl.training.bank.api.OperationCart";
    private static final String BANK_JNDI_NAME = "java:/bank/BankService!pl.training.bank.api.Bank";
    private static final String BANK_QUEUE_JNDI_NAME = "jms/queue/Bank";
    private static final String QUEUE_CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";

    public static void main(String[] args) throws NamingException {
        ProxyFactory proxyFactory = new ProxyFactory();
        Bank bank = proxyFactory.createProxy(BANK_JNDI_NAME);

        Account firstAccount = bank.createAccount();
        Account secondAccount = bank.createAccount();
        bank.deposit(1000, firstAccount.getNumber());
        bank.deposit(1000, secondAccount.getNumber());
        bank.withdraw(50, firstAccount.getNumber());
        bank.transfer(50, firstAccount.getNumber(), secondAccount.getNumber());

        Operation operation = new Operation(firstAccount, OperationType.DEPOSIT, 1000);

        ConnectionFactory connectionFactory = proxyFactory.createProxy(QUEUE_CONNECTION_FACTORY_JNDI_NAME);
        Queue queue = proxyFactory.createProxy(BANK_QUEUE_JNDI_NAME);
        try (JMSContext jmsContext = connectionFactory.createContext()) {
            jmsContext.createProducer().send(queue, operation);
        }

        OperationCart cart = proxyFactory.createProxy(OPERATION_CART_JNDI_NAME);
        cart.add(new Operation(firstAccount, OperationType.DEPOSIT, 500));
        cart.add(new Operation(firstAccount, OperationType.WITHDRAW, 10));
        cart.submit();

        // cart.cancel();

        System.out.printf("First account: %d\n", bank.getBalance(firstAccount.getNumber()));
        System.out.printf("Second account: %d\n", bank.getBalance(secondAccount.getNumber()));
    }

}
