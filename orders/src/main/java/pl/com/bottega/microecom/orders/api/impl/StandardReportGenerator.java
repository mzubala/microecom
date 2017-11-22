package pl.com.bottega.microecom.orders.api.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import pl.com.bottega.microecom.orders.api.ReportGenerator;
import pl.com.bottega.microecom.orders.model.Report;
import pl.com.bottega.microecom.orders.model.ReportRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class StandardReportGenerator implements ReportGenerator {

    private ReportRepository reportRepository;

    private ApplicationEventPublisher publisher;

    private AsyncReportGenerator asyncReportGenerator;

    public StandardReportGenerator(ReportRepository reportRepository, ApplicationEventPublisher publisher, AsyncReportGenerator asyncReportGenerator) {
        this.reportRepository = reportRepository;
        this.publisher = publisher;
        this.asyncReportGenerator = asyncReportGenerator;
    }

    @Override
    public Optional<Report> find(LocalDate date) {
        return reportRepository.findByDate(date);
    }

    @Override
    @Transactional
    public void generate(LocalDate date) {
        Optional<Report> report = find(date);
        report.orElseGet(() -> {
            Report newReport = new Report(date);
            reportRepository.save(newReport);
            asyncReportGenerator.generateInBackground(newReport);
            return newReport;
        });
    }

}
