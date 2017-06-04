package pl.com.bottega.microecom.orders.api;

import pl.com.bottega.microecom.orders.model.Product;

public interface CatalogFacade {

    Product getProduct(Long id);

}
