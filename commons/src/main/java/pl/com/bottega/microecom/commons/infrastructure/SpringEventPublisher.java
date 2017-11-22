package pl.com.bottega.microecom.commons.infrastructure;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import pl.com.bottega.microecom.commons.model.events.Event;
import pl.com.bottega.microecom.commons.model.events.EventPublisher;

// TODO Zadanie 4
public class SpringEventPublisher implements EventPublisher {

    private ApplicationEventPublisher publisher;

    public SpringEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(Event event) {
        publisher.publishEvent(event);
    }

}
