package com.hb.web.client;

import com.hb.web.client.fallback.BusinessTestServiceClientFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gateway", fallbackFactory = BusinessTestServiceClientFallBackFactory.class)
public interface IBusinessTestServiceClient {

    @GetMapping("/business-service/service/service/businessTestService/getPasswordByUserName/{userName}")
    String getPasswordByUserName(@PathVariable("userName") String userName);

}
