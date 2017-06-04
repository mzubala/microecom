package pl.com.bottega.microecom.orders.ui;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.microecom.orders.api.report.CsvOrderReporter;
import pl.com.bottega.microecom.orders.api.report.Report;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private CsvOrderReporter reporter;

    public ReportController(CsvOrderReporter reporter) {
        this.reporter = reporter;
    }

    @GetMapping("/{date}")
    public Report get(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return reporter.getReport(date);
    }

    @GetMapping("/{date}/csv")
    public void getCsv(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, HttpServletResponse response) throws IOException {
        Report report = reporter.getReport(date);
        if (report == null || !report.isGenerated()) {
            response.sendError(404);
        } else {
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=\"report.csv\"");
            response.getOutputStream().write(report.getBytes());
            response.flushBuffer();
        }
    }

    @PostMapping("/{date}/generation")
    public void schedule(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate date) {
        reporter.scheduleReport(date);
    }

}
