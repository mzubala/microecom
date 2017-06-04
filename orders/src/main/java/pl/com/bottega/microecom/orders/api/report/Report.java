package pl.com.bottega.microecom.orders.api.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.com.bottega.microecom.commons.model.BaseEntity;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Report extends BaseEntity {

    @JsonIgnore
    private byte[] bytes;

    private LocalDate date;

    private boolean generated;

    Report() {
    }

    public Report(LocalDate date) {
        this.bytes = bytes;
        this.date = date;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isGenerated() {
        return generated;
    }

    public void generated(byte[] bytes) {
        this.bytes = bytes;
        generated = true;
    }
}
