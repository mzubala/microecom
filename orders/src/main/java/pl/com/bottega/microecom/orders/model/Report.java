package pl.com.bottega.microecom.orders.model;

import pl.com.bottega.microecom.commons.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import java.time.LocalDate;

@Entity
public class Report extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ReportStatus status = ReportStatus.GENERATING;
    @Lob
    private byte[] content;
    private LocalDate date;

    Report() {}

    public Report(LocalDate date) {
        this.date = date;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
