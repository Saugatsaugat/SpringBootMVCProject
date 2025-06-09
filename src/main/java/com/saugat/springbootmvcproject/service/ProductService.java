package com.saugat.springbootmvcproject.service;

import com.saugat.springbootmvcproject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saugat.springbootmvcproject.repo.ProductRepo;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public Product getProduct(int productId){
        return repo.findById(productId).orElse(new Product());
    }

    public List<Product> getAll(){
        return repo.findAll();
    }

    public Product addProduct(Product product){
        return repo.save(product);
    }

    public Product update(Product product){
        repo.save(product);
        return product;
    }

    public void delete(int productId){
        repo.deleteById(productId);
    }

}
