package pl.com.bottega.microecom.orders.infrastructure;

import pl.com.bottega.microecom.commons.model.Money;
import pl.com.bottega.microecom.orders.model.OrderExporter;
import pl.com.bottega.microecom.orders.model.OrderStatus;
import pl.com.bottega.microecom.orders.model.Product;

public class CSVOrderExporter implements OrderExporter {
    private String CSV;

    @Override
    public void start() {

    }

    @Override
    public void exportId(Long id) {

    }

    @Override
    public void exportTotal(Money total) {

    }

    @Override
    public void exportCustomerId(Long id) {

    }

    @Override
    public void exportStatus(OrderStatus orderStatus) {

    }

    @Override
    public void exportOrderLine(Product product, int quantity) {

    }

    @Override
    public void finish() {

    }

    public String getCSV() {
        return "";
    }
}
