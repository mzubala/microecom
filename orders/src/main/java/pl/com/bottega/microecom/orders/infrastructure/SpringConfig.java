package pl.com.bottega.microecom.orders.infrastructure;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;
import pl.com.bottega.microecom.catalogclient.CatalogClient;
import pl.com.bottega.microecom.commons.infrastructure.JpaInjectingListener;
import pl.com.bottega.microecom.commons.infrastructure.SpringEventPublisher;

import javax.jms.ConnectionFactory;
import java.util.concurrent.Executor;

@Configuration
@EnableJms
@EnableAsync
public class SpringConfig extends AsyncConfigurerSupport {

    @Bean
    public CatalogClient catalogClient(RestTemplate restTemplate) {
        return new CatalogClient(restTemplate);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // TODO Zadanie 4 - zadeklaruj JpaInjectingListener i EventPublisher
    @Bean
    public SpringEventPublisher springEventPublisher(ApplicationEventPublisher publisher) {
        return new SpringEventPublisher(publisher);
    }

    @Bean
    public JpaInjectingListener jpaInjectingListener(AutowireCapableBeanFactory f) {
        return new JpaInjectingListener(f);
    }

    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Microecom-Orders-Async-Executor");
        executor.initialize();
        return executor;
    }

}
