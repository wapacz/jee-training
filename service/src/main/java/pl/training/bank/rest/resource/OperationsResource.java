package pl.training.bank.rest.resource;

import lombok.Setter;
import pl.training.bank.entity.Operation;
import pl.training.bank.service.Mapper;
import pl.training.bank.operation.OperationDto;
import pl.training.bank.service.operation.OperationsExecutorService;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Collections;

@Setter
@Path("operations")
public class OperationsResource {

    @EJB
    private OperationsExecutorService executorService;

    @POST
    public Response executeOperation(@Valid OperationDto operationDto) {
        executorService.submit(Collections.singletonList(operationDto));
        return Response.noContent().build();
    }

}
