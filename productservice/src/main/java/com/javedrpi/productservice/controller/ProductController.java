package com.javedrpi.productservice.controller;

import com.javedrpi.productservice.dto.ProductDto;
import com.javedrpi.productservice.entity.Product;
import com.javedrpi.productservice.service.ProductService;
import com.javedrpi.productservice.service.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com">...</a>
 */

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final TemplateRepository repository;

    @GetMapping("all2")
    public Flux<Product> getAll2(){
        return repository.findAll();
    }

    @GetMapping("all")
    public Flux<ProductDto> getAll(){
        return productService.getAll();
    }

    @GetMapping("price-range")
    public Flux<ProductDto> getAllByPriceRange(@PathVariable int min, @PathVariable int max){
        return productService.getProductsByPriceRange(Range.closed(min, max));
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> getProductById(@PathVariable  String id){
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ProductDto> insertProduct(@RequestBody Mono<ProductDto> productDtoMono){
        return productService.insertProduct(productDtoMono);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> updateProduct(@PathVariable String id, @RequestBody Mono<ProductDto> productDtoMono){
        return productService.updateProduct(id, productDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return productService.deleteProduct(id);
    }
}
