package pl.training.bank.rest.extension;

import pl.training.bank.api.ExceptionDto;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.stream.Collectors;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    private static final String DESCRIPTION = "Validation exception: ";

    @Override
    public Response toResponse(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(", "));
        ExceptionDto exceptionDto = new ExceptionDto(DESCRIPTION + message);
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(exceptionDto)
                .build();
    }

}
