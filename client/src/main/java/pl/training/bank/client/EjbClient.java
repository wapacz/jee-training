package pl.training.bank.client;

import pl.training.bank.api.Bank;
import pl.training.bank.api.BankAsync;
import pl.training.bank.api.OperationsCart;
import pl.training.bank.api.dto.AccountDto;
import pl.training.bank.api.dto.OperationDto;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.NamingException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static pl.training.bank.api.dto.OperationTypeDto.DEPOSIT;
import static pl.training.bank.api.dto.OperationTypeDto.WITHDRAW;

public class EjbClient {

    private static final String OPERATIONS_CART_JNDI_NAME = "java:/bank/CartService!pl.training.bank.api.OperationsCart";
    private static final String BANK_JNDI_NAME = "java:/bank/BankService!pl.training.bank.api.Bank";
    private static final String BANK_ASYNC_JNDI_NAME = "java:/bank/BankServiceAsync!pl.training.bank.api.BankAsync";
    private static final String BANK_QUEUE_JNDI_NAME = "jms/queue/Bank";
    private static final String QUEUE_CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";

    public static void main(String[] args) throws NamingException, ExecutionException, InterruptedException {
        ProxyFactory proxyFactory = new ProxyFactory();
        Bank bank = proxyFactory.createProxy(BANK_JNDI_NAME);

        AccountDto primaryAccount = bank.createAccount();
        AccountDto secondaryAccount = bank.createAccount();

        bank.processOperation(new OperationDto(primaryAccount, DEPOSIT, 1_000L));
        bank.processOperation(new OperationDto(secondaryAccount, DEPOSIT, 2_000L));
        bank.processOperation(new OperationDto(primaryAccount, DEPOSIT, 1_000L));

        OperationDto transferOperation =  new OperationDto(primaryAccount, DEPOSIT, 1_000L);
        transferOperation.setSecondaryAccount(secondaryAccount);
        bank.processOperation(transferOperation);

        ConnectionFactory connectionFactory = proxyFactory.createProxy(QUEUE_CONNECTION_FACTORY_JNDI_NAME);
        Queue queue = proxyFactory.createProxy(BANK_QUEUE_JNDI_NAME);
        try (JMSContext jmsContext = connectionFactory.createContext()) {
            jmsContext.createProducer().send(queue, new OperationDto(primaryAccount, DEPOSIT, 100L));
        }

        OperationsCart cart = proxyFactory.createProxy(OPERATIONS_CART_JNDI_NAME);
        cart.add(new OperationDto(primaryAccount, DEPOSIT, 500L));
        cart.add(new OperationDto(primaryAccount, WITHDRAW, 200L));
        cart.submit();

        System.out.printf("First account: %d\n", bank.getBalance(primaryAccount.getNumber()));
        System.out.printf("Second account: %d\n", bank.getBalance(secondaryAccount.getNumber()));

        BankAsync bankAsync =  proxyFactory.createProxy(BANK_ASYNC_JNDI_NAME);
        Future<List<OperationDto>> operationsSummary = bankAsync.generateOperationsReport();
        System.out.println("Report is done: " + operationsSummary.isDone());
        operationsSummary.get().forEach(System.out::println);
    }

}
