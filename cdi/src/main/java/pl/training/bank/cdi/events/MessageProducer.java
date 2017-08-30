package pl.training.bank.cdi.events;

import javax.enterprise.event.Event;
import javax.inject.Inject;

public class MessageProducer {

    @Inject
    private Event<String> eventEmitter;

    public void send(String message) {
        eventEmitter.fire(message);
    }

}
