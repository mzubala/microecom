package pl.com.bottega.microecom.orders.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.com.bottega.microecom.commons.model.BaseRepository;

import java.time.LocalDate;

public interface OrderRepository extends BaseRepository<Order, Long> {

    @Query("SELECT o.id FROM Order o JOIN o.orderItems i " +
            "WHERE i.product.productId = :productId " +
            "AND o.orderStatus = :orderStatus")
    Iterable<Long> findByProductIdAndOrderStatus(
            @Param("productId") Long productId,
            @Param("orderStatus") OrderStatus orderStatus
    );

    Iterable<Order> findByPlacedAt(LocalDate placedAt);
}
