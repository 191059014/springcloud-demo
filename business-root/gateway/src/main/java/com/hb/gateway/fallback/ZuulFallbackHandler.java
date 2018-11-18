package com.hb.gateway.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/**
 * 统一处理服务熔断
 */
@Component
public class ZuulFallbackHandler implements FallbackProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause != null) {
            String reason = cause.getMessage();
            logger.info("Excption {}", reason);
        }
        return new ZuulClientHttpResponse(route);
    }

}
