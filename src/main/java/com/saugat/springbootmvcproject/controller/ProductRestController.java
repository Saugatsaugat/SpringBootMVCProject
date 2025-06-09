package com.saugat.springbootmvcproject.controller;

import com.saugat.springbootmvcproject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.saugat.springbootmvcproject.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    @Autowired
    private ProductService service;

    @GetMapping("/ping")
    public String ping(){
        return "Product Rest Controller Class";
    }

    @GetMapping("/products")
    public List<Product> getAll(){
        return service.getAll();
    }

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable("productId") int productId){
        return service.getProduct(productId);
    }

    @PostMapping("/product")
    public Product addProduct(Product product){
        return service.addProduct(product);
    }

    @PutMapping("/product")
    public Product update(Product product){
        return service.update(product);
    }

    @DeleteMapping("/product/{productId}")
    public void delete(@PathVariable("productId") int productId){
        service.delete(productId);
    }

}
