package pl.training.bank.api;

import pl.training.bank.entity.OperationSummary;

import java.util.List;
import java.util.concurrent.Future;

public interface BankAsync {

    Future<List<OperationSummary>> generateOperationsReport();

}
