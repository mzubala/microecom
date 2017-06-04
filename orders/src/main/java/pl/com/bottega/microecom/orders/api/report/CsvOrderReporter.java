package pl.com.bottega.microecom.orders.api.report;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.microecom.orders.model.Order;
import pl.com.bottega.microecom.orders.model.OrderRepository;

import java.time.LocalDate;

@Component
public class CsvOrderReporter {

    private final CsvOrderReportGenerator generator;
    private OrderRepository orderRepository;
    private ReportRepository reportRepository;

    public CsvOrderReporter(
            ReportRepository reportRepository,
            CsvOrderReportGenerator generator,
            OrderRepository orderRepository) {
        this.reportRepository = reportRepository;
        this.generator = generator;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void scheduleReport(LocalDate date) {
        if(getReport(date) == null) {
            Report r = new Report(date);
            reportRepository.save(r);
            generateReport(r);
        }
    }

    @Async
    @Transactional
    public void generateReport(Report report) {
        Iterable<Order> orders = orderRepository.findByPlacedAt(report.getDate());
        report.generated(generator.generate(orders));
        reportRepository.save(report);
    }

    public Report getReport(LocalDate date) {
        return reportRepository.findByDate(date);
    }

}
