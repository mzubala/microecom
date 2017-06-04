package pl.com.bottega.microecom.orders.api;

import pl.com.bottega.microecom.orders.model.Order;

public interface BIFacade {

    void itemAddedToOrder(Long productId, int quantity);

    void itemsPurchased(Order order);

}
