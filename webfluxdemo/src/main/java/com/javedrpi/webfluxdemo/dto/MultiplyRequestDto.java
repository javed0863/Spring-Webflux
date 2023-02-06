package com.javedrpi.webfluxdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author Javed Ameen Shaikh
 * @website https://www.javedrpi.com
 */

@Data
@ToString
@AllArgsConstructor
public class MultiplyRequestDto {
    private int first;
    private int second;
}
