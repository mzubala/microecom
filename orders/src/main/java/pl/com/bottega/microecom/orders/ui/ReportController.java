package pl.com.bottega.microecom.orders.ui;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.microecom.orders.api.ReportGenerator;
import pl.com.bottega.microecom.orders.model.Report;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/reports/{date}")
public class ReportController {

    private ReportGenerator generator;

    public ReportController(ReportGenerator generator) {
        this.generator = generator;
    }

    @GetMapping
    public ResponseEntity<Report> get(@PathVariable Date date) {
        Optional<Report> reportOptional = generator.find(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        if(reportOptional.isPresent())
            return new ResponseEntity<Report>(reportOptional.get(), HttpStatus.OK);
        else
            return new ResponseEntity<Report>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public void generate(@PathVariable  Date date) {
        generator.generate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

}
