package pl.com.bottega.microecom.orders.model;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface ReportRepository extends CrudRepository<Report, Long> {

    Report findByDate(LocalDate date);

}
