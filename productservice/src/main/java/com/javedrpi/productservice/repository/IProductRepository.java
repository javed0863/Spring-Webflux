package com.javedrpi.productservice.repository;

import com.javedrpi.productservice.dto.ProductDto;
import com.javedrpi.productservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IProductRepository extends ReactiveMongoRepository<Product, String> {
    Flux<ProductDto> findByPriceBetween(Range<Integer> closed);

    Flux<Product> findByDescriptionContainsIgnoreCase(String name, Pageable page);
}
