package pl.com.bottega.microecom.catalog.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.com.bottega.microecom.catalog.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
