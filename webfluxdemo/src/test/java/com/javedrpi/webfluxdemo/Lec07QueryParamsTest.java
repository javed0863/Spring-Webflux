package com.javedrpi.webfluxdemo;

import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.net.URI;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com">...</a>
 */

public class Lec07QueryParamsTest extends BaseTest{

    @Test
    void queryParamsTest() {
        final Map<String, Integer> count = Map.of(
                "count", 5,
                "page", 10
         );

        final Flux<Integer> flux = webClient.get()
//                .uri(b -> b.path("/jobs/search").query("count={count}&page={page}").build(5, 10))
                .uri(b -> b.path("/jobs/search").query("count={count}&page={page}").build(count))
                .retrieve()
                .bodyToFlux(Integer.class)
                .doOnNext(System.out::println);

        StepVerifier.create(flux)
                .expectNextCount(2)
                .verifyComplete();
    }
}
