package pl.com.bottega.microecom.orders.model;

import com.google.common.base.Objects;
import pl.com.bottega.microecom.commons.model.Money;

import javax.persistence.*;

@Embeddable
public class Product {

    private Long productId;

    private String productName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "productPriceValue")),
            @AttributeOverride(name = "currencyCode", column = @Column(name = "productCurrencyCurrency"))
    })
    private Money productPrice;

    Product() {}

    public Product(Long productId, String name, Money price) {
        this.productId = productId;
        this.productName = name;
        this.productPrice = price;
    }

    public String getName() {
        return productName;
    }

    public Money getPrice() {
        return productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equal(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productId);
    }

    public Long getProductId() {
        return productId;
    }
}
