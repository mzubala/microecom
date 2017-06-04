package pl.com.bottega.microecom.catalog.controllers;

import pl.com.bottega.microecom.commons.api.MoneyDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ProductRequest {

    @NotNull
    private String name;

    @Valid
    @NotNull
    private MoneyDto price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Valid
    public MoneyDto getPrice() {
        return price;
    }

    public void setPrice(MoneyDto price) {
        this.price = price;
    }
}
