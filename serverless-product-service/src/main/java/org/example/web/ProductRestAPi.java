package org.example.web;

import org.example.entities.Product;
import org.example.reposotories.ProductRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductRestAPi {
    private ProductRepo productRepo;

    public ProductRestAPi(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    @GetMapping("/all")
    public List<Product> productList(){
        return this.productRepo.findAll();
    }
}
