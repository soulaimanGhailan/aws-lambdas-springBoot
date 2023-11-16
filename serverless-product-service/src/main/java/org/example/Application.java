package org.example;

import org.example.entities.Category;
import org.example.entities.Product;
import org.example.reposotories.CategoryRepo;
import org.example.reposotories.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import org.example.controller.PingController;

import java.util.List;
import java.util.UUID;


@SpringBootApplication
// We use direct @Import instead of @ComponentScan to speed up cold starts
// @ComponentScan(basePackages = "org.example.controller")
@Import({ PingController.class })
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner start(CategoryRepo categoryRepo , ProductRepo productRepo){
        return args -> {
            List.of("Clothing" , "furniture" , "electronics")
                    .forEach(s -> categoryRepo.save(Category.builder().name(s).build()));
            categoryRepo.findAll().forEach(category -> {
                for (int i = 0; i < 2; i++) {
                    productRepo.save(Product.builder()
                            .id(UUID.randomUUID().toString())
                            .name("product"+ i)
                            .price(Math.random()*1000 +20)
                            .quantity((int)(Math.random()*100 +100))
                            .category(category)
                            .build());
                }
            });
        };
    }
}