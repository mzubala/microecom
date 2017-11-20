package pl.com.bottega.microecom.orders.ui;

import org.springframework.stereotype.Component;
import pl.com.bottega.microecom.orders.api.ProductChangeHandler;

@Component
public class CatalogListener {

    private ProductChangeHandler productChangeHandler;

    public CatalogListener(ProductChangeHandler productChangeHandler) {
        this.productChangeHandler = productChangeHandler;
    }

    // TODO Zadanie 5 odbierz komunikat jms o zmianie produktu i użyj productChangeHandler

}
