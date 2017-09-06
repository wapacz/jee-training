package pl.training.bank.client;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import pl.training.bank.account.AccountDto;
import pl.training.bank.rest.extension.BinaryMapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClient {

    public static void main(String[] args) {
        ResteasyClient resteasyClient = new ResteasyClientBuilder()
                .register(BinaryMapper.class)
                .build();

        ResteasyWebTarget target = resteasyClient.target("http://localhost:8080/bank/api-v1/accounts/1");
        Response response = target.request()
                .accept(MediaType.APPLICATION_XML_TYPE)
                //.accept(MediaType.APPLICATION_JSON_TYPE)
                //.accept(BinaryMapper.APPLICATION_BINARY_DATA)
                .get();
        System.out.println(response.readEntity(AccountDto.class));
    }

}
