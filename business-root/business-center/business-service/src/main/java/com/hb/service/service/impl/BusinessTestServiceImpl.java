package com.hb.service.service.impl;

import com.hb.service.service.IBusinessTestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("service/service/businessTestService")
public class BusinessTestServiceImpl implements IBusinessTestService {

    @Override
    @GetMapping("/getPasswordByUserName/{userName}")
    public String getPasswordByUserName(@PathVariable("userName") String userName) {
        return "123456";
    }

}
