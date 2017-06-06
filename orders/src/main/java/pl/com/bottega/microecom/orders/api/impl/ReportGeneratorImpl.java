package pl.com.bottega.microecom.orders.api.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.microecom.orders.api.ReportGenerator;
import pl.com.bottega.microecom.orders.model.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDate;

@Component
public class ReportGeneratorImpl implements ReportGenerator {

    private ReportRepository reportRepository;
    private OrderRepository orderRepository;

    public ReportGeneratorImpl(ReportRepository reportRepository, OrderRepository orderRepository) {
        this.reportRepository = reportRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    @Async
    public void generate(LocalDate date) {
        if (reportRepository.findByDate(date) == null) {
            Report report = new Report(date);
            reportRepository.save(report);
            generateAsync(report);
        }
    }

    private void generateAsync(Report report) {
        report.setStatus(ReportStatus.READY);
        Iterable<Order> orders = orderRepository.findByPlacedAt(report.getDate());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(bos), CSVFormat.DEFAULT);
            for (Order order : orders) {
                CSVRecordOrderExporter exporter = new CSVRecordOrderExporter();
                order.export(exporter);
                Object[] record = exporter.getRecord();
                csvPrinter.printRecord(record);
            }
            Thread.sleep(20 * 1000);
            csvPrinter.flush();
            csvPrinter.close();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        report.setContent(bos.toByteArray());
        reportRepository.save(report);
    }

    @Override
    public ReportStatus getReportStaus(LocalDate date) {
        Report report = reportRepository.findByDate(date);
        if(report == null)
            return ReportStatus.NOT_FOUND;
        return report.getStatus();
    }

    @Override
    public Report getReport(LocalDate date) {
        return reportRepository.findByDate(date);
    }
}
