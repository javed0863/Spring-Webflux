package com.javedrpi.productservice.service;

import com.javedrpi.productservice.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com">...</a>
 */

@Service
@RequiredArgsConstructor
public class DataSetupService implements CommandLineRunner {

    private final ProductService service;

    @Override
    public void run(String... args) throws Exception {
        final ProductDto macbook = ProductDto.builder().price(3000).description("Macbook").build();
        final ProductDto iPad = ProductDto.builder().price(900).description("iPad").build();
        final ProductDto iWatch = ProductDto.builder().price(700).description("iWatch").build();
        final ProductDto appleTv = ProductDto.builder().price(200).description("AppleTv").build();
        final ProductDto airpods = ProductDto.builder().price(300).description("Airpods").build();
        final ProductDto iphone = ProductDto.builder().price(2000).description("iphone").build();

        Flux.just(macbook, iPad, iWatch, appleTv, airpods, iphone)
                .flatMap(o -> service.insertProduct(Mono.just(o)))
                .subscribe(System.out::println);

    }
}
