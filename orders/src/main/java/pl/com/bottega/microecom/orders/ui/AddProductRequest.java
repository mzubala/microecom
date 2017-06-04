package pl.com.bottega.microecom.orders.ui;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AddProductRequest {

    @NotNull
    private Long productId;

    @NotNull
    @Min(value = 1)
    private Integer quantity;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
