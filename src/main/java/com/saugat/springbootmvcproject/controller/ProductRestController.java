package com.saugat.springbootmvcproject.controller;

import com.saugat.springbootmvcproject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.saugat.springbootmvcproject.service.ProductService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") int productId){
        return new ResponseEntity<>(service.getProduct(productId), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageData){
        try {
            service.addProduct(product, imageData);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<?> update(@PathVariable("productId") int productId, @RequestPart Product product, @RequestPart MultipartFile imageData ){
        try {
            service.updateProduct(productId, product, imageData);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{productId}")
    public void delete(@PathVariable("productId") int productId){
        service.delete(productId);
    }

    @GetMapping("/productImageById/{productId}")
    public ResponseEntity<byte[]> getProductImageById(@PathVariable("productId") int productId){
        Product product = service.getProduct(productId);
        if(product.getImageData()!=null){
            return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
