package com.javedrpi.productservice.controller;

import com.javedrpi.productservice.entity.Product;
import com.javedrpi.productservice.service.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("mongoTemplate")
@RequiredArgsConstructor
public class MongoTemplateController {
    private final TemplateRepository repository;

    @GetMapping("findAll")
    public Flux<Product> findAll(){
        return repository.findAll();
    }

    @GetMapping("updateAllProdRef")
    public Flux<Product> updateAll(){
        return repository.updateAllProductRef();
    }

    @GetMapping("updateAll")
    public Flux<Product> updateAllProductRefUsingProdIdAndDesc(){
        return repository.updateAllProductRefUsingProdIdAndDesc();
    }
}
