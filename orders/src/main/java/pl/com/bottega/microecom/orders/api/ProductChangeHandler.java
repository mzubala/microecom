package pl.com.bottega.microecom.orders.api;

import pl.com.bottega.microecom.catalogclient.ProductInfo;

public interface ProductChangeHandler {

    void updateOrders(Long productId);

}
