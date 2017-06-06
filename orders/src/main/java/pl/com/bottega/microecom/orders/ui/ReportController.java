package pl.com.bottega.microecom.orders.ui;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.microecom.orders.api.ReportGenerator;
import pl.com.bottega.microecom.orders.model.Report;
import pl.com.bottega.microecom.orders.model.ReportStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/reports/{date}")
public class ReportController {

    private ReportGenerator reportGenerator;

    public ReportController(ReportGenerator reportGenerator) {
        this.reportGenerator = reportGenerator;
    }

    @PostMapping
    public void generate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        reportGenerator.generate(date);
    }

    @GetMapping
    public ReportStatus getStatus(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return reportGenerator.getReportStaus(date);
    }

    @GetMapping("/csv")
    public void getReport(
            @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            HttpServletResponse response
    ) throws IOException {
        Report report = reportGenerator.getReport(date);
        if(report.getStatus() == ReportStatus.READY) {
            response.setContentType("text/csv");
            response.getOutputStream().write(report.getContent());
            response.flushBuffer();
        }
        else
            response.sendError(404, "no report generated");
    }

}
