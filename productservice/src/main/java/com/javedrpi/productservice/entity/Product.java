package com.javedrpi.productservice.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com">...</a>
 */

@Data
@ToString
@Builder
public class Product {
    @Id
    private String id;
    private String description;
    private Integer price;
}
