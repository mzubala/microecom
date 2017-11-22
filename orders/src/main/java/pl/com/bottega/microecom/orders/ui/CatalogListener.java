package pl.com.bottega.microecom.orders.ui;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import pl.com.bottega.microecom.commons.model.events.ProductChangedEvent;
import pl.com.bottega.microecom.orders.api.ProductChangeHandler;

@Component
public class CatalogListener {

    private ProductChangeHandler productChangeHandler;

    public CatalogListener(ProductChangeHandler productChangeHandler) {
        this.productChangeHandler = productChangeHandler;
    }

    // TODO Zadanie 5 odbierz komunikat jms o zmianie produktu i użyj productChangeHandler
    @JmsListener(destination = "product-changed")
    public void productChanged(ProductChangedEvent event) {
        productChangeHandler.updateOrders(event.getProductId());
    }

}
