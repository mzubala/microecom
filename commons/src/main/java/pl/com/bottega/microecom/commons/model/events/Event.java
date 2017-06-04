package pl.com.bottega.microecom.commons.model.events;


import pl.com.bottega.microecom.commons.model.BaseAggregateRoot;

import java.time.LocalDateTime;

public abstract class Event {

    private LocalDateTime publishedAt = LocalDateTime.now();

    private Long aggregateId;

    public Event(BaseAggregateRoot aggregate) {
        this.aggregateId = aggregate.getId();
    }

    public Event(Long aggregateId) {
        this.aggregateId = aggregateId;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public Long getAggregateId() {
        return aggregateId;
    }
}
