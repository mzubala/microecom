package pl.com.bottega.microecom.orders.infrastructure;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import pl.com.bottega.microecom.orders.api.BIFacade;
import pl.com.bottega.microecom.orders.model.Order;

@Component
public class StandardBIFacade implements BIFacade {
    @Override
    public void itemAddedToOrder(Long productId, int quantity) {
        Logger.getLogger(getClass()).info("item added to order");
    }

    @Override
    public void itemsPurchased(Order order) {
        Logger.getLogger(getClass()).info("item purchased");
    }
}
