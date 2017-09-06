package pl.training.bank.service;

import lombok.Setter;
import pl.training.bank.api.Bank;
import pl.training.bank.entity.Account;
import pl.training.bank.entity.Operation;
import pl.training.bank.api.dto.AccountDto;
import pl.training.bank.api.dto.OperationDto;
import pl.training.bank.service.account.AccountsService;
import pl.training.bank.service.operation.OperationsExecutorService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(portName = "Bank", serviceName = "Bank")
@Setter
@Stateless
public class BankService implements Bank {

    @EJB
    private AccountsService accountsService;
    @EJB
    private OperationsExecutorService operationsExecutorService;
    @EJB
    private Mapper mapper;

    @Override
    public AccountDto createAccount() {
        Account account = accountsService.createAccount();
        return mapper.map(account, AccountDto.class);
    }

    @Override
    public void processOperations(List<OperationDto> operationsDtos) {
        List<Operation> operations = mapper.map(operationsDtos, Operation.class);
        operationsExecutorService.submit(operations);
    }

    @Override
    public void processOperation(OperationDto operationDto) {
        processOperations(Collections.singletonList(operationDto));
    }

    @Override
    public long getBalance(String accountNumber) {
        return accountsService.getBalance(accountNumber);
    }

    @PostConstruct
    public void init() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "BankService is ready...");
    }

    @PreDestroy
    public void destroy() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "BankService is going down...");
    }

}
