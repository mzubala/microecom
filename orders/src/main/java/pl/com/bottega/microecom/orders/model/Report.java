package pl.com.bottega.microecom.orders.model;

import javax.annotation.Generated;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Report {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private ReportStatus status = ReportStatus.GENERATING;

    private String csv;

    Report() {}

    public Report(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void generated(String csv) {
        this.csv = csv;
        status = ReportStatus.GENERATED;
    }
}
