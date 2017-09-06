package pl.training.bank.rest.extension;

import pl.training.bank.api.ExceptionDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SecurityExceptionMapper implements ExceptionMapper<SecurityException> {

    private static final String DESCRIPTION = "Authentication failed";

    @Override
    public Response toResponse(SecurityException securityException) {
        ExceptionDto exceptionDto = new ExceptionDto(DESCRIPTION);
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(exceptionDto).build();
    }

}
