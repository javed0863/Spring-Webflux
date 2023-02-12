package com.javedrpi.productservice.repository;

import com.javedrpi.productservice.entity.Product;
import com.javedrpi.productservice.service.ProductService;
import com.javedrpi.productservice.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com">...</a>
 */


@Service
public class PagableRepository {
    @Autowired
    private PageUtils pageUtils;

    public Mono<Page<Product>> findAll(Pageable pageable) {
        Query query = new Query();
        query.with(pageable);
        return pageUtils.toPage(query, pageable, Product.class);
    }
}
