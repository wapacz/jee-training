package pl.training.bank.rest.jwt;

import java.security.Key;

public interface KeyGenerator {

    Key generateKey();

}
