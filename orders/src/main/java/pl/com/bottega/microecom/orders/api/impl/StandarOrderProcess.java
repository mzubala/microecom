package pl.com.bottega.microecom.orders.api.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.microecom.commons.model.events.EventPublisher;
import pl.com.bottega.microecom.orders.api.CatalogFacade;
import pl.com.bottega.microecom.orders.api.OrderProcess;
import pl.com.bottega.microecom.orders.model.Order;
import pl.com.bottega.microecom.orders.model.OrderRepository;
import pl.com.bottega.microecom.orders.model.Product;

@Component
@Transactional
public class StandarOrderProcess implements OrderProcess {

    private OrderRepository orderRepository;
    private CatalogFacade catalogFacade;
    private EventPublisher eventPublisher;

    public StandarOrderProcess(OrderRepository orderRepository, CatalogFacade catalogFacade, EventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.catalogFacade = catalogFacade;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Long create(Long userId) {
        Order order = new Order(userId, eventPublisher);
        orderRepository.save(order);
        return order.getId();
    }

    @Override
    public void addProduct(Long orderId, Long productId, int quantity) {
        Order order = orderRepository.get(orderId);
        Product product = catalogFacade.getProduct(productId);
        order.addProduct(product, quantity);
    }

    @Override
    public void place(Long orderId) {
        Order order = orderRepository.get(orderId);
        order.place();
    }
}
