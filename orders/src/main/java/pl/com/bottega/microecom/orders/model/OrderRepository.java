package pl.com.bottega.microecom.orders.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface OrderRepository extends CrudRepository<Order, Long> {
    default Order get(Long orderId) {
        Order o = findOne(orderId);
        if (o == null)
            throw new OrderNotFoundExcetption();
        else
            return o;
    }

    @Query("SELECT o.id FROM Order o JOIN o.orderItems i " +
            "WHERE i.product.productId = :productId " +
            "AND o.orderStatus = :orderStatus")
    Iterable<Long> findByProductIdAndOrderStatus(
            @Param("productId") Long productId,
            @Param("orderStatus") OrderStatus orderStatus
    );

    Iterable<Order> findByPlacedAt(LocalDate placedAt);
}
