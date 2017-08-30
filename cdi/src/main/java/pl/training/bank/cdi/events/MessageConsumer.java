package pl.training.bank.cdi.events;

import lombok.Setter;

import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

@Setter
public class MessageConsumer {

    @Inject
    private Logger logger;

    public void onMessage(@Observes(notifyObserver = Reception.ALWAYS) String message) {
        logger.log(Level.INFO, "New message: " + message);
    }

}
