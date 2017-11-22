package pl.com.bottega.microecom.commons.model.events;

public class ProductChangedEvent {

    private Long productId;
    private String message;

    ProductChangedEvent() {

    }

    public ProductChangedEvent(Long productId, String message) {
        this.productId = productId;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}


