package pl.com.bottega.microecom.orders.ui;

public class CreateOrderResponse {

    private Long orderId;

    public CreateOrderResponse(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}
