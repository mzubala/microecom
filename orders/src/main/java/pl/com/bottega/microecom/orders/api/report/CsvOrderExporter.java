package pl.com.bottega.microecom.orders.api.report;

import org.apache.commons.csv.CSVPrinter;
import pl.com.bottega.microecom.commons.model.Money;
import pl.com.bottega.microecom.orders.model.OrderExporter;
import pl.com.bottega.microecom.orders.model.OrderStatus;
import pl.com.bottega.microecom.orders.model.Product;

import java.io.IOException;

public class CsvOrderExporter implements OrderExporter {

    private CSVPrinter printer;
    private Object[] row;

    public CsvOrderExporter(CSVPrinter printer) {
        this.printer = printer;

    }

    @Override
    public void start() {
        row = new Object[4];
    }

    @Override
    public void exportId(Long id) {
        row[0] = id;
    }

    @Override
    public void exportTotal(Money total) {
        row[2] = total.toString();
    }

    @Override
    public void exportCustomerId(Long id) {
        row[1] = id;
    }

    @Override
    public void exportStatus(OrderStatus orderStatus) {
        row[3] = orderStatus.name();
    }

    @Override
    public void exportOrderLine(Product product, int quantity) {

    }

    @Override
    public void finish() {
        try {
            printer.printRecord(row);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
