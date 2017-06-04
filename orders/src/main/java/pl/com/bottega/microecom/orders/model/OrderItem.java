package pl.com.bottega.microecom.orders.model;

import pl.com.bottega.microecom.commons.model.Money;

import javax.persistence.*;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

@Entity
class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Product product;

    private int quantity;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "itemTotalValue")),
            @AttributeOverride(name = "currencyCode", column = @Column(name = "itemTotalCurrency"))
    })
    private Money itemTotal;

    OrderItem() {
    }

    OrderItem(Product product, int quantity) {
        checkNotNull(product);
        checkState(quantity > 0);
        this.product = product;
        this.quantity = quantity;
        inc(quantity);
    }

    public void inc(int addtionoalQuantity) {
        this.quantity += addtionoalQuantity;
        calculateTotal();
    }

    private void calculateTotal() {
        this.itemTotal = product.getPrice().multiplyBy(this.quantity);
    }

    public boolean contains(Product product) {
        return product != null && this.product.equals(product);
    }

    public Money getItemTotal() {
        return itemTotal;
    }

    public void updateProduct(Product product) {
        this.product = product;
        calculateTotal();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
