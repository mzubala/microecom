package pl.com.bottega.microecom.orders.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.microecom.catalogclient.CatalogClient;
import pl.com.bottega.microecom.catalogclient.ProductInfo;
import pl.com.bottega.microecom.orders.api.CatalogFacade;
import pl.com.bottega.microecom.orders.api.ProductNotFoundException;
import pl.com.bottega.microecom.orders.model.Product;

@Component
public class StandardCatalogFacade implements CatalogFacade {

    private CatalogClient catalogClinet;

    public StandardCatalogFacade(CatalogClient catalogClinet) {
        this.catalogClinet = catalogClinet;
    }

    @Override
    public Product getProduct(Long id) {
        try {
            ProductInfo productInfo = catalogClinet.getProduct(id);
            return new Product(
                    productInfo.getId(),
                    productInfo.getName(),
                    productInfo.getPrice().toMoney()
            );
        } catch (Exception ex) {
            throw new ProductNotFoundException(ex);
        }
    }
}
