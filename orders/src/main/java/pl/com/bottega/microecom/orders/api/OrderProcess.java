package pl.com.bottega.microecom.orders.api;

public interface OrderProcess {

     Long create(Long userId);

     void addProduct(Long orderId, Long productId, int quantity);

     void place(Long orderId);

}
