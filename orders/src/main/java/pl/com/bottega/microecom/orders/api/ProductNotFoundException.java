package pl.com.bottega.microecom.orders.api;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Exception ex) {
        super(ex);
    }
}
