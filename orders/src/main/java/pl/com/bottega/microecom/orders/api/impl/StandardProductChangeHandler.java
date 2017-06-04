package pl.com.bottega.microecom.orders.api.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.microecom.catalogclient.ProductInfo;
import pl.com.bottega.microecom.orders.api.CatalogFacade;
import pl.com.bottega.microecom.orders.api.ProductChangeHandler;
import pl.com.bottega.microecom.orders.model.Order;
import pl.com.bottega.microecom.orders.model.OrderRepository;
import pl.com.bottega.microecom.orders.model.OrderStatus;
import pl.com.bottega.microecom.orders.model.Product;

@Component
public class StandardProductChangeHandler implements ProductChangeHandler {

    private OrderRepository orderRepository;
    private CatalogFacade catalogFacade;

    public StandardProductChangeHandler(OrderRepository orderRepository, CatalogFacade catalogFacade) {
        this.orderRepository = orderRepository;
        this.catalogFacade = catalogFacade;
    }

    @Transactional
    public void updateOrders(Long productId) {
        Product product = catalogFacade.getProduct(productId);
        Iterable<Long> oids =  orderRepository.findByProductIdAndOrderStatus(productId, OrderStatus.NEW);
        Iterable<Order> orders = orderRepository.findAll(oids);
        for(Order order : orders) {
            order.updateProduct(product);
        }
    }

}
