package pl.training.bank.rest.resource;

import lombok.Setter;
import pl.training.bank.entity.Operation;
import pl.training.bank.rest.Mapper;
import pl.training.bank.rest.dto.OperationDto;
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
    @EJB
    private Mapper mapper;

    @POST
    public Response executeOperation(@Valid OperationDto operationDto) {
        Operation operation = mapper.map(operationDto, Operation.class);
        executorService.submit(Collections.singletonList(operation));
        return Response.noContent().build();
    }

}
