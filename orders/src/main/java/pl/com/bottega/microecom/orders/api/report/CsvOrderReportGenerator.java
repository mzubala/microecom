package pl.com.bottega.microecom.orders.api.report;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;
import pl.com.bottega.microecom.orders.model.Order;
import pl.com.bottega.microecom.orders.model.OrderExporter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

@Component
public class CsvOrderReportGenerator {

    public byte[] generate(Iterable<Order> orders) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(os), CSVFormat.DEFAULT);
            for(Order order : orders) {
                OrderExporter exporter = new CsvOrderExporter(csvPrinter);
                order.export(exporter);
                csvPrinter.flush();
            }
            csvPrinter.close();
            return os.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
