package pl.training.bank.cdi;

import pl.training.bank.cdi.di.Car;
import pl.training.bank.cdi.di.Vehicle;
import pl.training.bank.cdi.events.MessageProducer;
import pl.training.bank.cdi.producer.RandomValue;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

@Startup
@Singleton
public class TestBean {

    @Inject
    private Car car;
    @RandomValue
    @Inject
    private int randomInt;
    @Inject
    private String randomText;
    @Inject
    private Logger logger;
    @Inject
    private MessageProducer messageProducer;

    @PostConstruct
    public void init() {
        car.drive();
        car.stop();
        logger.log(Level.INFO, "Random int: "  + randomInt);
        logger.log(Level.INFO, "Random text: "  + randomText);
        messageProducer.send("CDI is great!");
    }

}
