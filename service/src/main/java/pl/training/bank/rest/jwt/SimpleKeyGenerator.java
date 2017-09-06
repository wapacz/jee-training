package pl.training.bank.rest.jwt;

import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.Dependent;
import java.security.Key;

@Dependent
public class SimpleKeyGenerator implements KeyGenerator {

    @Override
    public Key generateKey() {
        String keyString = "simplekey";
        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
        return key;
    }

}