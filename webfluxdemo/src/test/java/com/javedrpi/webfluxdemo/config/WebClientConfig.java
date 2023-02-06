package com.javedrpi.webfluxdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com">...</a>
 */

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient getWebClient(){
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
//                .defaultHeaders(h -> h.setBasicAuth("username", "password"))
                .filter(this::sessionToken)
                .build();
    }

/*    private Mono<ClientResponse> sessionToken(ClientRequest cr, ExchangeFunction ef){
        System.out.println("Generating session token");
        final ClientRequest request = ClientRequest.from(cr)
                .headers(h -> h.setBearerAuth("some-lengthy-jwt"))
                .build();

        return ef.exchange(request);
    }*/

    private Mono<ClientResponse> sessionToken(ClientRequest request, ExchangeFunction ex){
    //auth --> basic or oauth
        ClientRequest clientRequest = request.attribute("auth")
                .map(v -> v.equals("basic") ? withBasicAuth(request) : withOAuth(request))
                .orElse(request);
        return ex.exchange(clientRequest);
    }

    private ClientRequest withBasicAuth(ClientRequest request){
        return ClientRequest.from(request)
                .headers(h -> h.setBasicAuth("username", "password"))
                .build();
    }

    private ClientRequest withOAuth(ClientRequest request){
        return ClientRequest.from(request)
                .headers(h -> h.setBearerAuth("some-token"))
                .build();
    }

}
