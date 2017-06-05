package pl.com.bottega.microecom.commons.infrastructure;


import org.springframework.context.ApplicationEventPublisher;
import pl.com.bottega.microecom.commons.model.events.Event;
import pl.com.bottega.microecom.commons.model.events.EventPublisher;

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
