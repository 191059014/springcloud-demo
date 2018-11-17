package com.hb.style.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessTestController {

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${spring.datasource.hikari.data-source-class-name}")
    private String driveClassName;

    @GetMapping("/test")
    public String test() {
        return dialect;
    }

}
