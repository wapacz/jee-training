package pl.training.bank.api;

import pl.training.bank.rest.dto.AccountDto;
import pl.training.bank.rest.dto.OperationDto;

import javax.ejb.Remote;
import javax.jws.WebService;
import java.util.List;

@WebService(portName = "Bank", serviceName = "Bank")
@Remote
public interface Bank {

    AccountDto createAccount();

    void process(List<OperationDto> operationsDtos);

    long getBalance(String accountNumber);

}
