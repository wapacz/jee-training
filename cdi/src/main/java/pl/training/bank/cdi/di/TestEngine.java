package pl.training.bank.cdi.di;

import lombok.Setter;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

@Setter
@Alternative
@Motor(type = "diesel")
public class TestEngine implements Engine {

    @Inject
    private Logger logger;

    @Override
    public void start() {
        logger.log(Level.INFO, "Test engine started...");
    }

}
