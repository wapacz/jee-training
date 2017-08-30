package pl.training.bank.cdi.decorator;

import lombok.Setter;
import pl.training.bank.cdi.di.Car;
import pl.training.bank.cdi.di.Vehicle;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

@Setter
@Decorator
public abstract class SmartCar implements Vehicle {

    @Delegate
    @Inject
    private Car car;
    @Inject
    private Logger logger;

    @Override
    public void drive() {
        logger.log(Level.INFO, "Turn on lights...");
        car.drive();
    }

}