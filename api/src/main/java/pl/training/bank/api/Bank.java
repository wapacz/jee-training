package pl.training.bank.api;

import pl.training.bank.api.dto.AccountDto;
import pl.training.bank.api.dto.OperationDto;

import javax.ejb.Remote;
import javax.jws.WebService;
import java.util.List;

@WebService(portName = "Bank", serviceName = "Bank")
@Remote
public interface Bank {

    AccountDto createAccount();

    void processOperations(List<OperationDto> operationsDtos);

    void processOperation(OperationDto operationDto);

    long getBalance(String accountNumber);

}
