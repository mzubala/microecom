package pl.com.bottega.microecom.orders.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.com.bottega.microecom.orders.model.Report;
import pl.com.bottega.microecom.orders.model.ReportRepository;

import javax.transaction.Transactional;

@Component
public class AsyncReportGenerator {

    @Autowired
    private ReportRepository reportRepository;

    @Async
    @Transactional
    public void generateInBackground(Report report) {
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        report.generated("ljsldkfjsdflj");
        System.out.println("ksksksk");
        reportRepository.save(report);
    }

}
