package pl.com.bottega.microecom.catalog.controllers;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.microecom.catalog.model.Product;
import pl.com.bottega.microecom.catalog.repositories.ProductRepository;
import pl.com.bottega.microecom.commons.api.MoneyDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    // TODO Zadanie 5 wyslij message jms gdy produkt ulega zmianie

    private ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public void create(@Valid @RequestBody ProductRequest req) {
        Product product = new Product();
        product.setName(req.getName());
        product.setPrice(req.getPrice().toMoney());
        repository.save(product);
    }

    @PutMapping("/{id}")
    @Transactional
    public void update(@PathVariable Long id, @Valid @RequestBody ProductRequest req) {
        Product product = repository.findOne(id);
        product.setPrice(req.getPrice().toMoney());
        product.setName(req.getName());
    }


    @GetMapping("/{id}")
    public ModelMap get(@PathVariable Long id) {
        Product product = repository.findOne(id);
        ModelMap model = new ModelMap();
        model.addAttribute("name", product.getName());
        model.addAttribute("price", new MoneyDto(product.getPrice()));
        model.addAttribute("id", id);
        return model;
    }

}
