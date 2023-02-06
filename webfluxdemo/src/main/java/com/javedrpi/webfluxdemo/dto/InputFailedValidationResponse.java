package com.javedrpi.webfluxdemo.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com">...</a>
 */

@Data
@ToString
public class InputFailedValidationResponse {
    private int errorCode;
    private int input;
    private String message;
}
