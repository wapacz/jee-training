package pl.training.bank.cdi.di;

import lombok.Setter;
import pl.training.bank.cdi.interceptor.Measure;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

@Measure
@Setter
public class Car implements Vehicle {

    private Engine engine;
    @Inject
    private Logger logger;

    @Inject
    public void setEngine(@Motor(type = "diesel") Engine engine) {
        this.engine = engine;
    }

    @PostConstruct
    public void init() {
        logger.log(Level.INFO, "Engine is set: " + (engine != null));
    }

    @Override
    public void drive() {
        engine.start();
        logger.log(Level.INFO, "==================================>");
    }

    @Override
    public void stop() {
        logger.log(Level.INFO, "Stopped...");
    }

}
