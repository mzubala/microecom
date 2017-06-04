package pl.com.bottega.microecom.orders.model;

import pl.com.bottega.microecom.commons.model.Money;

public interface OrderExporter {

    void start();

    void exportId(Long id);

    void exportTotal(Money total);

    void exportCustomerId(Long id);

    void exportStatus(OrderStatus orderStatus);

    void exportOrderLine(Product product, int quantity);

    void finish();

}
