package pl.com.bottega.microecom.orders.infrastructure;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.logging.Logger;

@Component
@Aspect
public class JPAPerformanceAspect {

    private static final long THREASHOLD = 30;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Around("execution(* pl.com.bottega.microecom.orders.api.impl..*.*(..))")
    public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Statistics statistics = sessionFactory.getStatistics();
        statistics.setStatisticsEnabled(true);
        try {
            Object ret = joinPoint.proceed();
            checkQueries(statistics);
            statistics.setStatisticsEnabled(false);
            return ret;
        } catch (Throwable ex) {
            checkQueries(statistics);
            statistics.setStatisticsEnabled(false);
            throw ex;
        }
    }

    private void checkQueries(Statistics statistics) {
        if (statistics.getPrepareStatementCount() > THREASHOLD)
            Logger.getLogger(JPAPerformanceAspect.class.getName()).warning("!!!!!!!!!!!!!! TO MANY QUERIES !!!!!!!!!!!!!!");
    }

}
