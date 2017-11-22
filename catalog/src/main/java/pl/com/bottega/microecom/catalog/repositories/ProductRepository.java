package pl.com.bottega.microecom.catalog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import pl.com.bottega.microecom.catalog.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Page<Product> findAll(Specification<Product> specification, Pageable pageable);

}
