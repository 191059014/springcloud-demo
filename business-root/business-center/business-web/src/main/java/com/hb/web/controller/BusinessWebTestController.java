package com.hb.web.controller;

import com.hb.web.client.IBusinessTestServiceClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "1.0", description = "测试控制器")
@RestController
@RequestMapping("controller/web/businessWebTestController")
public class BusinessWebTestController {

    @Autowired
    private IBusinessTestServiceClient businessTestServiceClient;

    @ApiOperation(value = "获取密码", notes = "通过用户名获取密码")
    @GetMapping("/getPasswordByUserName/{userName}")
    public String getPasswordByUserName(@PathVariable("userName") String userName) {
        return businessTestServiceClient.getPasswordByUserName(userName);
    }

}
