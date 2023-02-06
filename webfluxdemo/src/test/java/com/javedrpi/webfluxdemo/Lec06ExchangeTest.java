package com.javedrpi.webfluxdemo;

import com.javedrpi.webfluxdemo.dto.InputFailedValidationResponse;
import com.javedrpi.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com">...</a>
 */

public class Lec06ExchangeTest extends BaseTest{
    @Test
    void badRequestTest() {
        final Mono<Object> responseMono = webClient.get()
                .uri("/reactive-math/square/{number}/throw", 5)
                .exchangeToMono(this::exchange)
                .doOnNext(System.out::println)
                .doOnError(err -> System.out.println(err.getMessage()));

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();

    }

    private Mono<Object> exchange(ClientResponse cr){
        if(cr.statusCode().value() == 400)
            return cr.bodyToMono(InputFailedValidationResponse.class);
        else
            return cr.bodyToMono(Response.class);
    }
}
