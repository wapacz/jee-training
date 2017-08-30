package pl.training.bank.cdi.di;

import lombok.Setter;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

@Setter
@Motor(type = "petrol")
public class PetrolEngine implements Engine {

    @Inject
    private Logger logger;

    @Override
    public void start() {
       logger.log(Level.INFO, "Petrol engine started...");
    }

}
