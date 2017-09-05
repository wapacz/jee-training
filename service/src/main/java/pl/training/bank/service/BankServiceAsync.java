package pl.training.bank.service;

import lombok.Setter;
import pl.training.bank.api.BankAsync;
import pl.training.bank.entity.OperationSummary;
import pl.training.bank.service.operation.ReportingService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

@Setter
@Stateless
public class BankServiceAsync implements BankAsync {

    @EJB
    private ReportingService reportingService;

    @Asynchronous
    @Override
    public Future<List<OperationSummary>> generateOperationsReport() {
        return reportingService.generate();
    }

    @PostConstruct
    public void init() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "BankServiceAsync is ready...");
    }

    @PreDestroy
    public void destroy() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "BankServiceAsync is going down...");
    }

}
