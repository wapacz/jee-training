package pl.training.bank.cdi.di;

import lombok.Setter;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

@Setter
@Motor(type = "diesel")
public class DieselEngine implements Engine {

    @Inject
    private Logger logger;

    @Override
    public void start() {
        logger.log(Level.INFO, "Klekleklekleek diesel engine started...");
    }

}
