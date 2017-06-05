package pl.com.bottega.microecom.commons.infrastructure;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import pl.com.bottega.microecom.commons.model.BaseEntity;
import pl.com.bottega.microecom.commons.model.events.EventPublisher;
import pl.com.bottega.microecom.commons.model.events.EventPublisherAware;

import javax.persistence.PostLoad;

public class JpaInjectingListener implements ApplicationContextAware {

    private static ApplicationContext ctx;

    @PostLoad
    public void injectServices(BaseEntity aggregateRoot) {
        if(aggregateRoot instanceof EventPublisherAware) {
            ((EventPublisherAware) aggregateRoot).setEventPublisher(eventPublisher());
        }
    }

    private EventPublisher eventPublisher() {
        return ctx.getBean(EventPublisher.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
