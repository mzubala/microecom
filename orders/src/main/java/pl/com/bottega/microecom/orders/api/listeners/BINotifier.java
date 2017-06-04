package pl.com.bottega.microecom.orders.api.listeners;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.com.bottega.microecom.orders.api.BIFacade;
import pl.com.bottega.microecom.orders.model.Order;
import pl.com.bottega.microecom.orders.model.OrderRepository;
import pl.com.bottega.microecom.orders.model.events.OrderPlacedEvent;
import pl.com.bottega.microecom.orders.model.events.ProductAddedToOrderEvent;

@Component
public class BINotifier {

    private BIFacade biFacade;
    private OrderRepository orderRepository;

    public BINotifier(BIFacade biFacade, OrderRepository orderRepository) {
        this.biFacade = biFacade;
        this.orderRepository = orderRepository;
    }

    @EventListener
    public void orderPlaced(OrderPlacedEvent event) {
        Order order = orderRepository.get(event.getAggregateId());
        biFacade.itemsPurchased(order);
    }

    @EventListener
    public void orderPlaced(ProductAddedToOrderEvent event) {
        Order order = orderRepository.get(event.getAggregateId());
        biFacade.itemAddedToOrder(event.getProductId(), event.getQuantity());
    }

}
