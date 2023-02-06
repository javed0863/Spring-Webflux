package com.javedrpi.webfluxdemo;

import com.javedrpi.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com">...</a>
 */

public class Lec02GetMultiResponseTest extends BaseTest{

    @Test
    void fluxTest() {
        final Flux<Response> responseMono = webClient.get()
                .uri("/reactive-math/table/{number}", 10)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseMono)
                .expectNextCount(10)
                .verifyComplete();
    }

    @Test
    void fluxStreamTest() {
        final Flux<Response> responseMono = webClient.get()
                .uri("/reactive-math/table/{number}/stream", 5)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseMono)
                .expectNextCount(10)
                .verifyComplete();
    }

}
