package pl.training.bank.service.operation;

import lombok.Setter;
import pl.training.bank.entity.OperationSummary;

import javax.ejb.AsyncResult;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.concurrent.Future;

@Setter
@Stateless
public class ReportService {

    private static final String GET_OPERATIONS_SUMMARY_QL = "select new pl.training.bank.entity.OperationSummary(o.type, sum(o.funds)) from Operation o group by o.type";

    @PersistenceContext(unitName = "bank")
    private EntityManager entityManager;

    public Future<List<OperationSummary>> generate() {
        try {
            Thread.sleep(10_000); // only for training !!!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<OperationSummary> result = entityManager.createQuery(GET_OPERATIONS_SUMMARY_QL, OperationSummary.class).getResultList();
        return new AsyncResult<>(result);
    }

}
