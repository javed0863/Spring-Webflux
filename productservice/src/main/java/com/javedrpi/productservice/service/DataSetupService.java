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
        final ProductDto macbook = ProductDto.builder().price(3000).description("Apple Macbook").build();
        final ProductDto iPad = ProductDto.builder().price(900).description("Apple iPad").build();
        final ProductDto iWatch = ProductDto.builder().price(700).description("Apple iWatch").build();
        final ProductDto appleTv = ProductDto.builder().price(200).description("Apple Tv").build();
        final ProductDto airpods = ProductDto.builder().price(300).description("Apple Airpods").build();
        final ProductDto iphone = ProductDto.builder().price(2000).description("Apple iphone").build();
        final ProductDto fenix = ProductDto.builder().price(1200).description("Garmin Fenix").build();
        final ProductDto nike = ProductDto.builder().price(140).description("Nike Shoes").build();
        final ProductDto levisJacket = ProductDto.builder().price(70).description("Levis Jacket").build();
        final ProductDto camera = ProductDto.builder().price(2100).description("Camera").build();
        final ProductDto bulb = ProductDto.builder().price(10).description("Bulb").build();
        final ProductDto bicycle = ProductDto.builder().price(6000).description("Bicycle").build();
        final ProductDto mirror = ProductDto.builder().price(100).description("Mirror").build();
        final ProductDto chair = ProductDto.builder().price(40).description("Chair").build();
        final ProductDto printer = ProductDto.builder().price(50).description("Printer").build();
        final ProductDto tv = ProductDto.builder().price(600).description("Television").build();
        final ProductDto bed = ProductDto.builder().price(800).description("Bed").build();

        Flux.just(macbook, iPad, iWatch, appleTv, airpods, iphone,
                        fenix, nike, levisJacket,camera, bulb, bicycle,
                        mirror, chair, printer,tv, bed)
                .flatMap(o -> service.insertProduct(Mono.just(o)))
                .subscribe(System.out::println);

    }
}
