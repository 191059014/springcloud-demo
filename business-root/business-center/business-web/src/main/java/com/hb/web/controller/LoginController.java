package com.hb.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = "1.0",description = "登录")
public class LoginController {

    @ApiOperation(value = "跳转登录页面",notes = "仅仅跳转到登录页面")
    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @ApiOperation(value = "用户登录",notes = "根据用户名和密码进行登录")
    @GetMapping("/login/{userName}/{password}")
    @ResponseBody
    public String login(@PathVariable("userName")String userName,@PathVariable("password")String password){
        return "sucess";
    }

}
