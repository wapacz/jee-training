package pl.training.bank.service.operation;

import lombok.Setter;
import pl.training.bank.entity.OperationSummary;
import pl.training.bank.api.dto.OperationDto;
import pl.training.bank.service.Mapper;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.AsyncResult;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

@Setter
@Stateless
public class ReportingService {

    private static final String GET_OPERATIONS_SUMMARY_QL = "select new pl.training.bank.entity.OperationSummary(o.type, sum(o.funds)) from Operation o group by o.type";

    @PersistenceContext(unitName = "bank")
    private EntityManager entityManager;
    @EJB
    private Mapper mapper;

    public Future<List<OperationDto>> generate() {
        try {
            Thread.sleep(10_000); // only for training !!!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<OperationSummary> result = entityManager.createQuery(GET_OPERATIONS_SUMMARY_QL, OperationSummary.class).getResultList();
        List<OperationDto> operationsDtos = mapper.map(result, OperationDto.class);
        return new AsyncResult<>(operationsDtos);
    }

    @PostConstruct
    public void init() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "ReportingService is ready...");
    }

    @PreDestroy
    public void destroy() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "ReportingService is going down...");
    }

}
