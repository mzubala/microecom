package pl.com.bottega.microecom.orders.ui;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import pl.com.bottega.microecom.catalogclient.ProductInfo;
import pl.com.bottega.microecom.orders.api.ProductChangeHandler;

@Component
public class CatalogListener {

    private ProductChangeHandler productChangeHandler;

    public CatalogListener(ProductChangeHandler productChangeHandler) {
        this.productChangeHandler = productChangeHandler;
    }

    @JmsListener(destination = "product-updated")
    public void productUpdated(Long productId) {
        productChangeHandler.updateOrders(productId);
        throw new IllegalArgumentException();
    }

}
