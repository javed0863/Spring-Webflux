package com.javedrpi.productservice.service;

import com.javedrpi.productservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com">...</a>
 */

@Service
public class TemplateRepository {

    @Autowired
    ReactiveMongoTemplate template;

    public Flux<Product> findAll() {
        return template.findAll(Product.class);
    }
}
