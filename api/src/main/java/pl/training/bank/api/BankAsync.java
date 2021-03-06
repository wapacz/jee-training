package pl.training.bank.api;

import pl.training.bank.operation.OperationDto;

import javax.ejb.Remote;
import java.util.List;
import java.util.concurrent.Future;

@Remote
public interface BankAsync {

    Future<List<OperationDto>> generateOperationsReport();

}
