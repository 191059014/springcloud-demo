package com.hb.base.service;

import org.springframework.util.concurrent.ListenableFuture;

/**
 * Created by huangbiao on 2018/10/5.
 */
public interface IServiceExcutorService {

    ListenableFuture<Object> executeFuture(Object serviceInstance, String methodName, Object... paramArr);

}
