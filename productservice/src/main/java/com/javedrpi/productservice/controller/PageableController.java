package com.javedrpi.productservice.controller;


import com.javedrpi.productservice.entity.Product;
import com.javedrpi.productservice.repository.PagableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("pageable")
@RequiredArgsConstructor
public class PageableController {

    private final PagableRepository repository;

    @GetMapping("page/{name}")
    public Mono<Map<String, Object>> getAllByDescription(@PathVariable String name,
                                                         @RequestParam(required = false, defaultValue = "0") int page,
                                                         @RequestParam(required = false, defaultValue = "0") int size){

        return Mono.<Page<Product>>create(emitter -> {
                    Mono<Page<Product>> result = repository.findAll(PageRequest.of(page, size));
                    result.subscribe(emitter::success);
                })
                .map(o -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("tutorials", o.getContent());
                    response.put("currentPage", o.getNumber());
                    response.put("totalItems", o.getTotalElements());
                    response.put("totalPages", o.getTotalPages());
                    return response;
                })
                .subscribeOn(Schedulers.boundedElastic());

    }
}
