package com.hb.base.service.impl;

import com.hb.base.service.IServiceExcutorService;
import org.apache.commons.beanutils.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by huangbiao on 2018/10/5.
 */
@Service("serviceExcutorService")
public class ServiceExcutorServiceImpl implements IServiceExcutorService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @Async
    public ListenableFuture<Object> executeFuture(Object serviceInstance, String methodName, Object... paramArr) {
        try {
            Object o = MethodUtils.invokeMethod(serviceInstance, methodName, paramArr);
            return new AsyncResult<Object>(o);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
