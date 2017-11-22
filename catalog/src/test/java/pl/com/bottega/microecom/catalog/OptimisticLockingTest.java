package pl.com.bottega.microecom.catalog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;
import pl.com.bottega.microecom.catalog.model.Product;
import pl.com.bottega.microecom.commons.model.Money;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OptimisticLockingTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private TransactionTemplate tt;

    @Test(expected = OptimisticLockException.class)
    public void showOptimisticLocking() {
        Product product = new Product("test", new Money(100));

        tt.execute((c) -> {
            em.persist(product);
            return null;
        });

        tt.execute((c) -> {
           product.setName("test 2");
           em.merge(product);
           return null;
        });

        tt.execute((c) -> {
            product.setName("test 3");
            em.merge(product);
            return null;
        });
    }

}
