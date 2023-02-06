package com.javedrpi.webfluxdemo;

import com.javedrpi.webfluxdemo.dto.MultiplyRequestDto;
import com.javedrpi.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com">...</a>
 */

public class Lec08AttributesTest extends BaseTest{
    @Test
    void postTest() {
        final Mono<Response> responseMono = webClient.post()
                .uri("/reactive-math/multiply")
                .bodyValue(new MultiplyRequestDto(5, 3))
                .attribute("auth", "oauth")
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseMono)
                .expectNextMatches(r -> r.getOutput() == 15)
                .verifyComplete();
    }
}
