package pl.com.bottega.microecom.orders.model.events;

import pl.com.bottega.microecom.commons.model.BaseAggregateRoot;
import pl.com.bottega.microecom.commons.model.events.Event;

public class ProductAddedToOrderEvent extends Event {

    private Long productId;
    private int quantity;

    public ProductAddedToOrderEvent(BaseAggregateRoot aggregate, Long productId, int quantity) {
        super(aggregate);
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
