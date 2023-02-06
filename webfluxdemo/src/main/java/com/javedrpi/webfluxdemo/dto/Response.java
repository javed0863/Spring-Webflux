package com.javedrpi.webfluxdemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author Javed Ameen Shaikh
 * @website https://www.javedrpi.com
 */

@Data
@ToString
@NoArgsConstructor
public class Response {
    private Date date = new Date();
    private int output;

    public Response(int output) {
        this.output = output;
    }
}
