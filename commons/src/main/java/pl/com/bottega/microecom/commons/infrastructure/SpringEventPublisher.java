package pl.com.bottega.microecom.commons.infrastructure;


import org.springframework.context.ApplicationEventPublisher;
import pl.com.bottega.microecom.commons.model.events.Event;
import pl.com.bottega.microecom.commons.model.events.EventPublisher;

public class SpringEventPublisher implements EventPublisher {

    private ApplicationEventPublisher applicationEventPublisher;

    public SpringEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(Event event) {
        applicationEventPublisher.publishEvent(event);
    }
}
