package pl.training.bank.rest.resource;

import lombok.Setter;
import pl.training.bank.entity.Account;
import pl.training.bank.rest.Mapper;
import pl.training.bank.rest.dto.AccountDto;
import pl.training.bank.service.account.AccountService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Setter
@Path("accounts")
public class AccountsResource {

    @EJB
    private AccountService accountService;
    @EJB
    private Mapper mapper;
    @Context
    private UriInfo uriInfo;

    @POST
    public Response createAccount() {
        Account account = accountService.createAccount();
        URI locationUri = currentUriWithId(account.getId());
        return Response.created(locationUri).build();
    }

    @GET
    @Path("{id:\\d+}")
    public AccountDto getById(@PathParam("id") Long id) {
        Account account = accountService.getAccountById(id);
        return mapper.map(account, AccountDto.class);
    }

    private URI currentUriWithId(Long id) {
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        return uriBuilder.path(id.toString()).build();
    }

}