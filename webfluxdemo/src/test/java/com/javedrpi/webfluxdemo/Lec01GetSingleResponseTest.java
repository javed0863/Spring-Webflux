package com.javedrpi.webfluxdemo;

import com.javedrpi.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com">...</a>
 */

public class Lec01GetSingleResponseTest extends BaseTest{

    @Test
    void blockTest() {
        final Response response = webClient.get()
                .uri("/reactive-math/square/{number}", 10)
                .retrieve()
                .bodyToMono(Response.class)
                .block();
        System.out.println(response);
    }

    @Test
    void stepVerifierTest() {
        final Mono<Response> responseMono = webClient.get()
                .uri("/reactive-math/square/{number}", 10)
                .retrieve()
                .bodyToMono(Response.class);
        StepVerifier.create(responseMono)
                .expectNextMatches( r -> r.getOutput() == 100)
                .verifyComplete();
    }
}
