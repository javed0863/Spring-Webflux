package com.javedrpi.productservice.service;

import com.javedrpi.productservice.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.Date;
import java.util.Objects;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com">...</a>
 */

@Service
@Slf4j
public class TemplateRepository {

    @Autowired
    ReactiveMongoTemplate template;

    public Flux<Product> findAll() {
        return template.findAll(Product.class);
    }

    public Flux<Product> updateAllProductRef() {
        log.info("Updating all... ");
        template.updateMulti(new Query(Criteria.where("pRef").isNull()), Update.update("pRef", "ref-12"), Product.class)
                .subscribe(updateResult -> log.info(updateResult.toString()));

        return template.findAll(Product.class);
    }

    public Flux<Product> updateAllProductRefUsingProdIdAndDesc() {
        log.info("Update Process Started ... ");
        long start = new Date().getTime();

        template.findDistinct("description", Product.class, String.class)
                .doOnNext(s -> log.info("Description: "+s))
                .flatMap(this::getProducts)
                .takeWhile(Objects::nonNull)
//                .limitRate(500)
                .flatMap(this::performUpdate)
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(
                        s -> log.info("Updated: " + s),
                        null,
                        () -> {
                            log.info(">> COMPLETED <<");
                            long end = new Date().getTime();
                            log.info("Total Time: "+(end-start) + " ms");
                        }
                );
        return Flux.empty();
    }

    private Flux<Product> getProducts(String s) {
        Query query = new Query(Criteria.where("pRef").isNull().and("description").is(s));
        return template.find(query, Product.class);
    }

    private Flux<Product> performUpdate(Product s) {
        log.info("Input: "+ s.toString());
        Query query = new Query(Criteria.where("id").is(s.getId()));
        return Flux.from(template.findAndModify(
                query,
                Update.update("pRef", s.getId() + "-" + s.getDescription()),
                Product.class
        ));
    }

}
