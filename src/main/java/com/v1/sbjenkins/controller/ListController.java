package com.v1.sbjenkins.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ListController {
    @GetMapping("/numbers")
    public List<Integer> getNumbers() {
        return Arrays.asList(0, 1, 2, 3, 4, 5);
    }
}
