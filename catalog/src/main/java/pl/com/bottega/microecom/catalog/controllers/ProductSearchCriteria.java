package pl.com.bottega.microecom.catalog.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.com.bottega.microecom.catalog.model.Product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class ProductSearchCriteria implements Specification<Product> {

    private String name;
    private BigDecimal priceFrom, priceTo;

    private int pageNumber = 0;

    private int perPage = 20;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(BigDecimal priceFrom) {
        this.priceFrom = priceFrom;
    }

    public BigDecimal getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(BigDecimal priceTo) {
        this.priceTo = priceTo;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate p = criteriaBuilder.conjunction();
        if (name != null) {
            p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("name"), name));
        }
        if (priceTo != null) {
            p = criteriaBuilder.and(p,
                    criteriaBuilder.lessThanOrEqualTo(
                            root.get("price").get("value"), priceTo));
        }
        if (priceFrom != null) {
            p = criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(
                    root.get("price").get("value"), priceFrom)
            );
        }
        return p;
    }
}
