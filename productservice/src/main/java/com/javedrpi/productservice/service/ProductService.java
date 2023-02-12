package com.javedrpi.productservice.service;

import com.javedrpi.productservice.dto.ProductDto;
import com.javedrpi.productservice.entity.Product;
import com.javedrpi.productservice.repository.IProductRepository;
import com.javedrpi.productservice.utils.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com">...</a>
 */

@Service
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;

    public Flux<ProductDto> getAll(){
        return productRepository.findAll().map(EntityUtil::toDto);
    }

    public Mono<ProductDto> getProductById(String id){
        return productRepository.findById(id).map(EntityUtil::toDto);
    }

    public Mono<ProductDto> insertProduct(Mono<ProductDto> dtoMono){
        return dtoMono.map(EntityUtil::toEntity)
                .flatMap(productRepository::insert)
                .map(EntityUtil::toDto);
    }

    public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> dtoMono){
        return productRepository.findById(id)
                .flatMap(
                        p -> dtoMono.map(EntityUtil::toEntity)
                                .doOnNext(e -> e.setId(id))
                )
                .flatMap(productRepository::save)
                .map(EntityUtil::toDto);
    }

    public Mono<Void> deleteProduct(String id){
        return productRepository.deleteById(id);
    }

    public Flux<ProductDto> getProductsByPriceRange(Range<Integer> closed) {
        return productRepository.findByPriceBetween(closed);
    }

    public Flux<Product> findByDescriptionContainsIgnoreCase(String name) {
        return productRepository.findByDescriptionContainsIgnoreCase(name, PageRequest.of(0,3));
    }
}
