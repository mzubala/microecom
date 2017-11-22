package pl.com.bottega.microecom.catalog;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.bottega.microecom.catalog.controllers.ProductSearchCriteria;
import pl.com.bottega.microecom.catalog.controllers.ProductsFinder;
import pl.com.bottega.microecom.catalog.model.Product;
import pl.com.bottega.microecom.catalog.repositories.ProductRepository;
import pl.com.bottega.microecom.commons.model.Money;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class SearchProductsTest {

    @Autowired
    private ProductsFinder finder;

    @Autowired
    private ProductRepository repository;

    private Product dresy, adidasy, baseball;

    @Before
    public void insertProducts() {
        dresy = new Product("dresy", new Money(100));
        repository.save(dresy);
        adidasy = new Product("adidasy", new Money(200));
        repository.save(adidasy);
        baseball = new Product("baseball", new Money(50));
        repository.save(baseball);
    }

    @Test
    public void searchesProductByName() {
        ProductSearchCriteria criteria = new ProductSearchCriteria();
        criteria.setName("dresy");

        Page<Product> page = finder.search(criteria);

        assertThat(page.getTotalElements()).isEqualTo(1L);
        assertThat(page.getContent()).contains(dresy);
    }

    @Test
    public void searchesProductsByPrice() {
        ProductSearchCriteria criteria = new ProductSearchCriteria();
        criteria.setPriceFrom(new BigDecimal(100));
        criteria.setPriceTo(new BigDecimal(200));

        Page<Product> page = finder.search(criteria);

        assertThat(page.getTotalElements()).isEqualTo(2L);
        assertThat(page.getContent()).contains(dresy, adidasy);
    }

    @Test
    public void paginatesProducts() {
        ProductSearchCriteria criteria = new ProductSearchCriteria();
        criteria.setPriceTo(new BigDecimal(300));
        criteria.setPageNumber(2);
        criteria.setPerPage(1);

        Page<Product> page = finder.search(criteria);

        assertThat(page.getTotalElements()).isEqualTo(3L);
        assertThat(page.getNumber()).isEqualTo(2);
        assertThat(page.getContent()).contains(baseball);
    }

}
