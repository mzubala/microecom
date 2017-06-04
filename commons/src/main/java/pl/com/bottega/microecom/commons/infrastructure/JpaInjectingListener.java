package pl.com.bottega.microecom.commons.infrastructure;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import pl.com.bottega.microecom.commons.model.BaseEntity;
import pl.com.bottega.microecom.commons.model.events.EventPublisher;
import pl.com.bottega.microecom.commons.model.events.EventPublisherAware;

import javax.persistence.PostLoad;

public class JpaInjectingListener {

    // TODO Zadanie 4 wstrzyknij do encji podsystem zdarzeń EventPublisher o ile
    // encja implementuje EventPublisherAware

    private static ApplicationContext ctx;

    @PostLoad
    public void injectServices(BaseEntity aggregateRoot) {

    }

}
