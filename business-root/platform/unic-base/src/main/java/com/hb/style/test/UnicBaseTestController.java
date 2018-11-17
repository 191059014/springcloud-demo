package com.hb.style.test;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("controller/base/unicBaseTestController")
public class UnicBaseTestController implements InitializingBean {

    @Value("${spring.datasource.hikari.data-source-class-name}")
    private String driveClassName;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UnicBaseTestController===" + driveClassName);
    }

    @GetMapping("/test")
    public void test() {

    }
}
