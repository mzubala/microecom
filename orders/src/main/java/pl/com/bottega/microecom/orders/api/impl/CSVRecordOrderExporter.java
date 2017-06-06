package pl.com.bottega.microecom.orders.api.impl;

import pl.com.bottega.microecom.commons.model.Money;
import pl.com.bottega.microecom.orders.model.OrderExporter;
import pl.com.bottega.microecom.orders.model.OrderStatus;
import pl.com.bottega.microecom.orders.model.Product;

public class CSVRecordOrderExporter implements OrderExporter {

    private Object[] record = new Object[4];

    public Object[] getRecord() {
        return record;
    }

    @Override
    public void start() {

    }

    @Override
    public void exportId(Long id) {
        record[0] = id;
    }

    @Override
    public void exportTotal(Money total) {
        record[1] = total.toString();
    }

    @Override
    public void exportCustomerId(Long id) {
        record[2] = id;
    }

    @Override
    public void exportStatus(OrderStatus orderStatus) {
        record[3] = orderStatus;
    }

    @Override
    public void exportOrderLine(Product product, int quantity) {

    }

    @Override
    public void finish() {

    }
}
