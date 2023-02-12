package com.javedrpi.productservice.utils;

import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com">...</a>
 */

@Component
public class PageUtils {
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    public void setReactiveMongoTemplate(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    public <T> Mono<Page<T>> toPage(Query query, Pageable pageable, Class<T> targetClass) {
        return reactiveMongoTemplate.count(query, targetClass)
                .map(aLong -> new MutablePair<List<T>, Long>(null, aLong)).
                flatMap(pair -> reactiveMongoTemplate.find(query, targetClass)
                                .collectList()
                                .map(list -> {
                                    pair.setLeft(list);
                                    return pair;
                                })
                                .flatMap(pagePair1 -> Mono.just((Page<T>) new PageImpl<>(pagePair1.getLeft(), pageable, pagePair1.getRight()))))
                .single();
    }
}
