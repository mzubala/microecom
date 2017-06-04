package pl.com.bottega.microecom.catalog.model;

import org.hibernate.validator.constraints.NotBlank;
import pl.com.bottega.microecom.commons.model.BaseAggregateRoot;
import pl.com.bottega.microecom.commons.model.Money;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Product extends BaseAggregateRoot {

    @NotBlank
    private String name;

    @Embedded
    @NotNull
    private Money price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }
}
