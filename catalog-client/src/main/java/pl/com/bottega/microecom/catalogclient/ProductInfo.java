package pl.com.bottega.microecom.catalogclient;

import pl.com.bottega.microecom.commons.api.MoneyDto;

public class ProductInfo {

    private Long id;
    private String name;
    private MoneyDto price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MoneyDto getPrice() {
        return price;
    }

    public void setPrice(MoneyDto price) {
        this.price = price;
    }
}
