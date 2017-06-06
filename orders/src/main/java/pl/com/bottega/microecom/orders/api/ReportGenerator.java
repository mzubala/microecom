package pl.com.bottega.microecom.orders.api;

import pl.com.bottega.microecom.orders.model.Report;
import pl.com.bottega.microecom.orders.model.ReportStatus;

import java.time.LocalDate;

public interface ReportGenerator {

    void generate(LocalDate date);

    ReportStatus getReportStaus(LocalDate date);

    Report getReport(LocalDate date);

}
