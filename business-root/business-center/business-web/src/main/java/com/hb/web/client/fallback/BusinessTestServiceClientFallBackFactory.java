package com.hb.web.client.fallback;

import com.hb.web.client.IBusinessTestServiceClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class BusinessTestServiceClientFallBackFactory implements FallbackFactory<IBusinessTestServiceClient> {

    @Override
    public IBusinessTestServiceClient create(Throwable throwable) {

        return new IBusinessTestServiceClient() {

            @Override
            public String getPasswordByUserName(String userName) {
                return "an error occurs[" + throwable.getClass().getName() + "]";
            }

        };

    }
}
