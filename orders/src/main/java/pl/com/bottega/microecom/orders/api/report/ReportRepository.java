package pl.com.bottega.microecom.orders.api.report;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface ReportRepository extends CrudRepository<Report, Long> {

    Report findByDate(LocalDate date);

}