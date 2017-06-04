package pl.com.bottega.microecom.orders.model.events;

import pl.com.bottega.microecom.commons.model.BaseAggregateRoot;
import pl.com.bottega.microecom.commons.model.events.Event;

import java.time.LocalDateTime;

public class OrderPlacedEvent extends Event {

    public OrderPlacedEvent(BaseAggregateRoot aggregate) {
        super(aggregate);
    }

}
