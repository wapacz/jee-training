package pl.training.bank.service.account;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Disables automatic synchronization of singleton methods
// @ConcurrencyManagement(ConcurrencyManagementType.BEAN)

@Startup  // Provides automatic start during deployment
@Singleton
public class FakeAccountNumberGenerator implements AccountNumberGenerator {

    private long counter;

    // @Lock(LockType.WRITE)
    @Override
    public String getNext() {
        return format(++counter);
    }

    @Lock(LockType.READ)
    @Override
    public String getLast() {
        return format(counter);
    }

    private String format(long value) {
        return String.format("%026d", value);
    }

    @PostConstruct
    public void init() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "FakeAccountNumberGenerator is ready...");
    }

    @PreDestroy
    public void destroy() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "FakeAccountNumberGenerator is going down...");
    }

}
