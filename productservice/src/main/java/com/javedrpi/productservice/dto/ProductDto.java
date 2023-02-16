package com.javedrpi.productservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com/me">Portfolio</a>
 */

@Data
@ToString
@Builder
public class ProductDto {
    private String id;
    private String description;
    private Integer price;
}

