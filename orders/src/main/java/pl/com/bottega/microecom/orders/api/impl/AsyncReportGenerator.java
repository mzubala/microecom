package pl.com.bottega.microecom.orders.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.com.bottega.microecom.orders.infrastructure.CSVOrderExporter;
import pl.com.bottega.microecom.orders.model.OrderExporter;
import pl.com.bottega.microecom.orders.model.OrderRepository;
import pl.com.bottega.microecom.orders.model.Report;
import pl.com.bottega.microecom.orders.model.ReportRepository;

import javax.transaction.Transactional;

@Component
public class AsyncReportGenerator {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Async
    @Transactional
    public void generateInBackground(Report report) {
        CSVOrderExporter csvOrderExporter = new CSVOrderExporter();
        orderRepository.findByPlacedAt(report.getDate()).forEach((order) -> {
            order.export(csvOrderExporter);
        });
        report.generated(csvOrderExporter.getCSV());
        reportRepository.save(report);
    }

}
