package pl.com.bottega.microecom.orders.api;

import pl.com.bottega.microecom.orders.model.Report;

import java.time.LocalDate;
import java.util.Optional;

public interface ReportGenerator {
    Optional<Report> find(LocalDate date);

    void generate(LocalDate date);
}
