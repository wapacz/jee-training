package pl.training.bank.rest.extension;

import pl.training.bank.account.AccountNotFoundException;
import pl.training.bank.api.dto.ExceptionDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AccountNotFoundExceptionMapper implements ExceptionMapper<AccountNotFoundException> {

    private static final String DESCRIPTION = "Account not found";

    @Override
    public Response toResponse(AccountNotFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto(DESCRIPTION);
        return Response.status(Response.Status.NOT_FOUND)
                .entity(exceptionDto)
                .build();
    }

}
