package com.saugat.springbootmvcproject.service;

import com.saugat.springbootmvcproject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saugat.springbootmvcproject.repo.ProductRepo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public void addProduct(Product product, MultipartFile imageData) throws IOException {
        product.setFilename(imageData.getOriginalFilename());
        product.setFiletype(imageData.getContentType());
        product.setImageData(imageData.getBytes());

        repo.save(product);
    }

    public void delete(int productId){
        repo.deleteById(productId);
    }

    public void updateProduct(int productId, Product product, MultipartFile imageData) throws IOException {
        product.setFilename(imageData.getOriginalFilename());
        product.setFiletype(imageData.getContentType());
        product.setImageData(imageData.getBytes());

        repo.save(product);
    }
}
