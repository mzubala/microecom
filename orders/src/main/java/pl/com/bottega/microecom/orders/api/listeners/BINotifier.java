package pl.com.bottega.microecom.orders.api.listeners;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.com.bottega.microecom.orders.api.BIFacade;
import pl.com.bottega.microecom.orders.model.Order;
import pl.com.bottega.microecom.orders.model.OrderRepository;
import pl.com.bottega.microecom.orders.model.events.OrderPlacedEvent;
import pl.com.bottega.microecom.orders.model.events.ProductAddedToOrderEvent;

@Component
@ConditionalOnExpression(value="${microecom.bi.active}")
//@ConditionalOnProperty(name="microecom.bi.active", havingValue = "true")
//@Conditional(BiActiveContition.class)
public class BINotifier {

    private BIFacade biFacade;
    private OrderRepository orderRepository;

    public BINotifier(BIFacade biFacade, OrderRepository orderRepository) {
        this.biFacade = biFacade;
        this.orderRepository = orderRepository;
    }

    // TODO Zadanie 4
    @EventListener
    public void productAdded(ProductAddedToOrderEvent productAddedToOrderEvent) {
        biFacade.itemAddedToOrder(
                productAddedToOrderEvent.getProductId(),
                productAddedToOrderEvent.getQuantity()
        );
    }

    @EventListener
    public void orderPlaced(OrderPlacedEvent orderPlacedEvent) {
        Order order = orderRepository.get(orderPlacedEvent.getAggregateId());
        biFacade.itemsPurchased(order);
    }

}
