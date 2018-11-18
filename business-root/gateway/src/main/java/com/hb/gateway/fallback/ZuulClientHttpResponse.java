package com.hb.gateway.fallback;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 服务响应
 */
public class ZuulClientHttpResponse implements ClientHttpResponse {

    private String routeName;

    public ZuulClientHttpResponse(String routeName) {
        this.routeName = routeName;
    }

    @Override
    public HttpStatus getStatusCode() throws IOException {
        return HttpStatus.OK;
    }

    @Override
    public int getRawStatusCode() throws IOException {
        return 200;
    }

    @Override
    public String getStatusText() throws IOException {
        return "OK";
    }

    @Override
    public void close() {

    }

    @Override
    public InputStream getBody() throws IOException {
        return new ByteArrayInputStream((routeName + " is unavailable.").getBytes());
    }

    @Override
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}
