package pl.training.bank.rest.resource;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pl.training.bank.entity.User;
import pl.training.bank.rest.jwt.KeyGenerator;
import pl.training.bank.rest.jwt.PasswordEncoder;
import pl.training.bank.security.TokenDto;
import pl.training.bank.security.UserDto;
import pl.training.bank.service.security.UserService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Transactional
@Path("/tokens")
public class TokensResource {

    @Context
    private UriInfo uriInfo;
    @EJB
    private UserService userService;
    @Inject
    private PasswordEncoder passwordEncoder;
    @Inject
    private KeyGenerator keyGenerator;

    @POST
    public Response authenticateUser(UserDto userDto) {
        String token = authenticate(userDto);
        return Response.ok().entity(new TokenDto(token)).build();
    }

    private String authenticate(UserDto userDto) {
        User user = userService.getUserByLogin(userDto.getLogin());
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        if (!user.getPassword().equals(encodedPassword)) {
            throw new SecurityException();
        }
        return createToken(userDto.getLogin());
    }

    private String createToken(String login) {
        Key key = keyGenerator.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(login)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        System.out.println(jwtToken);
        return jwtToken;
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
