package pl.training.bank.rest.resource;

import pl.training.bank.entity.User;
import pl.training.bank.security.UserDto;
import pl.training.bank.service.Mapper;
import pl.training.bank.service.security.UserService;

import javax.ejb.EJB;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Transactional
@Path("/users")
public class UsersResource {

    @EJB
    private UserService userService;
    @EJB
    private Mapper mapper;

    @POST
    public Response create(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        userService.addUser(user);
        return Response.noContent().build();
    }

}
