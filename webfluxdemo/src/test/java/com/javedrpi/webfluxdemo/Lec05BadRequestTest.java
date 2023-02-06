package com.javedrpi.webfluxdemo;

import com.javedrpi.webfluxdemo.dto.InputFailedValidationResponse;
import com.javedrpi.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Consumer;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com">...</a>
 */

public class Lec05BadRequestTest extends BaseTest{
    @Test
    void badRequestTest() {
        final Mono<Response> responseMono = webClient.get()
                .uri("/reactive-math/square/{number}/throw", 5)
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println)
                .doOnError(err -> System.out.println(err.getMessage()));

        StepVerifier.create(responseMono)
                .verifyError(WebClientResponseException.BadRequest.class);

    }

}
