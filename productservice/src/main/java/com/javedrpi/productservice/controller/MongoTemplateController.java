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

    @GetMapping("findAllUsingMongoTemplate")
    public Flux<Product> findAllUsingMongoTemplate(){
        return repository.findAll();
    }
}