package pl.training.bank.cdi.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class Beans {

    private Random random = new Random();

    // @Singleton // scope for random value / bean, default is set to @Dependent
    @RandomValue
    @Produces
    public int getRandomInt() {
        return random.nextInt(101);
    }

    public void destroyRandomInt(@Disposes @RandomValue int value, Logger logger) {
        logger.log(Level.INFO, "Destroing int: " + value);
    }

    @Produces
    public String getRandomText(@RandomValue int randomInt) {
        return "Text" + randomInt;
    }

    @Produces
    public Logger getLogger(InjectionPoint injectionPoint) {
        String className = injectionPoint.getMember().getDeclaringClass().getName();
        return Logger.getLogger(className);
    }

}
