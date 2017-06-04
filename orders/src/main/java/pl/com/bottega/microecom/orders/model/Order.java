package pl.com.bottega.microecom.orders.model;

import pl.com.bottega.microecom.commons.model.BaseAggregateRoot;
import pl.com.bottega.microecom.commons.model.Money;
import pl.com.bottega.microecom.commons.model.events.EventPublisher;
import pl.com.bottega.microecom.commons.model.events.EventPublisherAware;
import pl.com.bottega.microecom.orders.model.events.OrderPlacedEvent;
import pl.com.bottega.microecom.orders.model.events.ProductAddedToOrderEvent;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkState;

@Entity
@Table(name = "orders")
public class Order extends BaseAggregateRoot implements EventPublisherAware {

    @Transient
    private EventPublisher eventPublisher;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderColumn
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems = new LinkedList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.NEW;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "totalValue")),
            @AttributeOverride(name = "currencyCode", column = @Column(name = "totalCurrency"))
    })
    private Money total = Money.ZERO;

    private Long customerId;

    private LocalDate placedAt;

    Order() {
    }

    public Order(Long customerId, EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
        this.customerId = customerId;
    }

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void place() {
        checkState(orderStatus == OrderStatus.NEW, "only new orders can be placed");
        checkState(orderItems.size() > 0, "at least one item needs to be on the order");
        orderStatus = OrderStatus.PLACED;
        eventPublisher.publish(new OrderPlacedEvent(this));
    }

    public void addProduct(Product product, int count) {
        checkState(orderStatus == OrderStatus.NEW);
        Optional<OrderItem> item = orderItems.stream().filter((i) -> i.contains(product)).findFirst();
        item.ifPresent((i) -> i.inc(count));
        item.orElseGet(() -> {
            orderItems.add(new OrderItem(product, count));
            return null;
        });
        calculateTotal();
        placedAt = LocalDate.now();
        eventPublisher.publish(new ProductAddedToOrderEvent(this, product.getProductId(), count));
    }

    private void calculateTotal() {
        total = orderItems.stream().reduce(
                Money.ZERO,
                (amount, i) -> amount.add(i.getItemTotal()),
                (a1, a2) -> a1.add(a2)
        );
    }

    public void updateProduct(Product product) {
        for(OrderItem i : orderItems) {
            if(i.contains(product))
                i.updateProduct(product);
        }
        calculateTotal();
    }

    public void export(OrderExporter orderExporter) {
        orderExporter.start();
        orderExporter.exportCustomerId(customerId);
        orderExporter.exportStatus(orderStatus);
        orderExporter.exportTotal(total);
        orderItems.stream().forEach((i) -> {
            orderExporter.exportOrderLine(i.getProduct(), i.getQuantity());
        });
        orderExporter.finish();
    }
}
