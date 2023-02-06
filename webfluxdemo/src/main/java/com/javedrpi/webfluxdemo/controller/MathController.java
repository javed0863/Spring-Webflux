package com.javedrpi.webfluxdemo.controller;

import com.javedrpi.webfluxdemo.dto.Response;
import com.javedrpi.webfluxdemo.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Javed Ameen Shaikh
 * @website https://www.javedrpi.com
 */

@RestController
@RequestMapping("math")
public class MathController {
    @Autowired
    private MathService mathService;

    @GetMapping("square/{input}")
    public Response findSquare(@PathVariable int input){
        if(input < 10)
            throw new IllegalArgumentException();
        return this.mathService.findSquare(input);
    }

    @GetMapping("table/{input}")
    public List<Response> multiplicationTable(@PathVariable int input){
        return this.mathService.multiplicationTable(input);
    }
}
