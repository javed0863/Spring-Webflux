package com.javedrpi.productservice.utils;

import com.javedrpi.productservice.dto.ProductDto;
import com.javedrpi.productservice.entity.Product;
import org.springframework.beans.BeanUtils;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com">...</a>
 */

public class EntityUtil {
    public static ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public static Product toEntity(ProductDto dto) {
        return Product.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();
    }
}
