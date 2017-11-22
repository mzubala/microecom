package pl.com.bottega.microecom.catalog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import pl.com.bottega.microecom.catalog.model.Product;
import pl.com.bottega.microecom.catalog.repositories.ProductRepository;

@Component
public class ProductsFinder {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> search(ProductSearchCriteria criteria) {
        return productRepository.findAll(
                criteria,
                new PageRequest(criteria.getPageNumber(), criteria.getPerPage()));
    }

}
