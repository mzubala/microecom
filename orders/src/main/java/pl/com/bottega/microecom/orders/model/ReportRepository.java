package pl.com.bottega.microecom.orders.model;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ReportRepository extends CrudRepository<Report, Long> {

    Optional<Report> findByDate(LocalDate date);

}
